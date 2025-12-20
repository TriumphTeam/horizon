package dev.triumphteam.horizon.html.tag

import dev.triumphteam.horizon.html.visitor.TagVisitor

public actual interface TagRenderer : TagVisitor {
    public actual fun onTagStart(tag: HtmlTag)
    public actual fun onTagEnd(tag: HtmlTag)
    public actual fun onTagContent(tag: HtmlTag, content: CharSequence)
    public fun onTagEvent(tag: HtmlTag, event: String, function: String)
}

public class HTMLStringRenderer(
    public val prettyPrint: Boolean,
    public val xhtmlCompatible: Boolean,
) : TagRenderer {

    override val renderer: TagRenderer = this

    private val out = StringBuilder()

    private var level = 0
    private var ln = true

    override fun onTagStart(tag: HtmlTag) {
        if (prettyPrint && !tag.isInline) {
            indent()
        }
        level++

        out.append("<")
        out.append(tag.tagName)

        tag.attributes.forEach { key, value ->
            if (!key.isValidXmlAttributeName()) {
                throw IllegalArgumentException("Tag ${tag.tagName} has invalid attribute name $key")
            }

            out.append(' ')
            out.append(key)
            out.append("=\"")
            out.escapeAppend(value)
            out.append('\"')
        }

        if (xhtmlCompatible && tag.isEmpty) {
            out.append("/")
        }

        out.append(">")
        ln = false
    }

    override fun onTagEnd(tag: HtmlTag) {
        level--
        if (ln) {
            indent()
        }

        if (!tag.isEmpty) {
            out.append("</")
            out.append(tag.tagName)
            out.append(">")
        }

        if (prettyPrint && !tag.isInline) {
            appendLine()
        }
    }

    override fun onTagContent(tag: HtmlTag, content: CharSequence) {
        out.escapeAppend(content)
        ln = false
    }

    override fun onTagEvent(
        tag: HtmlTag,
        event: String,
        function: String,
    ) {

    }

    private fun appendLine() {
        if (prettyPrint && !ln) {
            out.append("\n")
            ln = true
        }
    }

    private fun indent() {
        if (prettyPrint) {
            if (!ln) {
                out.append("\n")
            }
            var remaining = level
            while (remaining >= 4) {
                out.append("        ")
                remaining -= 4
            }
            while (remaining >= 2) {
                out.append("    ")
                remaining -= 2
            }
            if (remaining > 0) {
                out.append("  ")
            }
            ln = false
        }
    }

    public fun render(): String = out.toString()
}

private val escapeMap = mapOf(
    '<' to "&lt;",
    '>' to "&gt;",
    '&' to "&amp;",
    '\"' to "&quot;",
).let { mappings ->
    val maxCode = mappings.keys.maxOfOrNull { it.code } ?: -1

    Array(maxCode + 1) { mappings[it.toChar()] }
}

internal fun Appendable.escapeAppend(value: CharSequence) {
    var lastIndex = 0
    val mappings = escapeMap
    val size = mappings.size

    var currentIndex = 0
    while (currentIndex < value.length) {
        val code = value[currentIndex].code

        if (code == '\\'.code && currentIndex + 1 < value.length && value[currentIndex + 1] == '&') {
            append(value.substring(lastIndex, currentIndex))
            check(currentIndex + 1 < value.length) { "String must not end with '\\'." }
            append(value[currentIndex + 1])
            lastIndex = currentIndex + 2
            currentIndex += 2
            continue
        }

        if (code !in 0..<size) {
            currentIndex++
            continue
        }

        val escape = mappings[code]
        if (escape != null) {
            append(value.substring(lastIndex, currentIndex))
            append(escape)
            lastIndex = currentIndex + 1
        }

        currentIndex++
    }

    if (lastIndex < value.length) {
        append(value.substring(lastIndex, value.length))
    }
}

private fun String.isValidXmlAttributeName() =
    this.isNotEmpty()
            && !startsWithXml()
            // See https://html.spec.whatwg.org/multipage/syntax.html#attributes-2 for which characters are forbidden
            // \u000C is the form-feed character. \f is not supported in Kotlin, so it's necessary to use the
            // unicode literal.
            && this.none { it in "\t\n\u000C />\"'=" }

private fun String.startsWithXml() = length >= 3
        && (this[0].let { it == 'x' || it == 'X' })
        && (this[1].let { it == 'm' || it == 'M' })
        && (this[2].let { it == 'l' || it == 'L' })
