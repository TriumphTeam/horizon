package dev.triumphteam.horizon.html

import dev.triumphteam.horizon.html.tag.HtmlTag
import dev.triumphteam.horizon.html.tag.HtmlTagElement
import dev.triumphteam.horizon.html.tag.createElement
import java.util.LinkedList

public actual interface TagRenderer : TagVisitor {
    public actual fun onStart(tag: HtmlTag)
    public actual fun onEnd(tag: HtmlTag)
    public actual fun onContent(tag: HtmlTag, content: CharSequence)
    public actual fun onAttribute(tag: HtmlTag, attribute: String, value: String?)
    public fun onTagEvent(tag: HtmlTag, event: String, function: String)
}

public class HtmlStringRenderer(
    public val prettyPrint: Boolean = false,
) : TagRenderer {

    private val path: MutableList<HtmlTagElement> = LinkedList()

    override val renderer: TagRenderer = this

    override fun onStart(tag: HtmlTag) {
        val element = createElement(tag.tagName, tag.isVoid)

        element.attributes.putAll(tag.attributes)

        path.lastOrNull()?.appendChild(element)
        path.add(element)
    }

    override fun onEnd(tag: HtmlTag) {
        val last = last()
        if (last.tagName.lowercase() != tag.tagName.lowercase()) {
            error("We started tag ${tag.tagName} but already trying to end it.")
        }

        if (path.size <= 1) return
        path.removeLast()
    }

    override fun onContent(tag: HtmlTag, content: CharSequence) {
        val last = last()

        if (last.tagName.lowercase() != tag.tagName.lowercase()) {
            error("Wrong current tag: current -> ${last.tagName}, given -> ${tag.tagName}.")
        }

        last.setContent(content)
    }

    override fun onAttribute(
        tag: HtmlTag,
        attribute: String,
        value: String?,
    ) {
        val last = last()

        if (last.tagName.lowercase() != tag.tagName.lowercase()) {
            error("Wrong current tag: current -> ${last.tagName}, given -> ${tag.tagName}.")
        }

        if (value == null) {
            last.removeAttribute(attribute)
            return
        }

        last.setAttribute(attribute, value)
    }

    override fun onTagEvent(
        tag: HtmlTag,
        event: String,
        function: String,
    ) {

    }

    internal fun render(): String = buildString {
        path.forEach {
            appendElement(0, it)
        }
    }

    private fun StringBuilder.appendElement(indentation: Int, element: HtmlTagElement) {
        val mainIndentation = " ".repeat(indentation)
        val contentIndentation = " ".repeat(indentation + 2)

        appendLine(
            buildString {
                append("$mainIndentation<${element.tagName}")
                element.attributes.forEach { (key, value) ->
                    when {
                        // Specific handler for booleans not having `= "true"`.
                        value.isEmpty() -> append(" $key")
                        else -> append(" $key=\"${value.escapeHtml()}\"")
                    }
                }
                append(">")
            },
        )

        if (element.isVoid) return

        when {
            element.children.isEmpty() -> {
                element.content.forEach { (_, content) ->
                    appendLine("$contentIndentation${content.joinToString("").escapeHtml()}")
                }
            }

            else -> {
                val initialContent = element.content[-1] ?: emptyList()
                if (initialContent.isNotEmpty()) {
                    appendLine("$contentIndentation${initialContent.joinToString("").escapeHtml()}")
                }

                element.children.forEachIndexed { index, child ->
                    appendElement(indentation + 2, child)

                    val content = element.content[index] ?: emptyList()
                    if (content.isNotEmpty()) {
                        appendLine("$contentIndentation${content.joinToString("").escapeHtml()}")
                    }
                }
            }
        }

        appendLine("$mainIndentation</${element.tagName}>")
    }

    private fun last(): HtmlTagElement = path.lastOrNull() ?: error("No current tag in the renderer.")
}

private fun CharSequence.escapeHtml(): String = this.toString()
    .replace("&", "&amp;")
    .replace("<", "&lt;")
    .replace(">", "&gt;")
    .replace("\"", "&quot;")
    .replace("'", "&#39;")
