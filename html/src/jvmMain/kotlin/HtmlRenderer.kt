package dev.triumphteam.horizon.html

import dev.triumphteam.horizon.html.tag.HtmlContentNode
import dev.triumphteam.horizon.html.tag.HtmlMarker
import dev.triumphteam.horizon.html.tag.HtmlTagNode
import dev.triumphteam.horizon.html.tag.createElement
import dev.triumphteam.horizon.html.tag.visit

public actual interface HtmlRenderer : HtmlConsumer {
    public actual fun onStart(tag: HtmlTag)
    public actual fun onEnd(tag: HtmlTag)
    public actual fun onContent(tag: HtmlTag, content: CharSequence)
    public actual fun onAttribute(tag: HtmlTag, attribute: String, value: String?)
    public actual fun createHtmlRenderer(): HtmlRenderer
    public fun onTagEvent(tag: HtmlTag, event: String, function: String)

    public fun render(): List<HtmlNode>
}

public inline fun createHtml(block: HtmlConsumer.() -> Unit): HtmlDocument {
    return HtmlDocument(content = HtmlDocumentRenderer().apply(block).render().first())
}

public open class HtmlDocumentRenderer : AbstractHtmlRenderer<HtmlTagNode, HtmlNode>() {

    override fun onTagStart(tag: HtmlTag) {
        val element = createElement(tag.tagName, tag.isVoid)
        tag.attributes.forEach { (key, value) -> element.setAttribute(key, value) }
        current = element
    }

    override fun onTagEnd(tag: HtmlTag) {
        val current = current ?: error("Trying to end tag ${tag.tagName} but it was never opened.")

        if (current.tagName.lowercase() != tag.tagName.lowercase()) {
            error("Tried to end tag ${tag.tagName} but ${current.tagName} is the current open.")
        }

        if (tag is HtmlConsumerTag) {
            current.appendChildren(tag.renderer.render())
        }

        elements.add(current)
    }

    override fun onContent(tag: HtmlTag, content: CharSequence) {
        elements.add(HtmlContentNode(content.toString()))
    }

    override fun onAttribute(
        tag: HtmlTag,
        attribute: String,
        value: String?,
    ) {
        val current =
            current ?: error("Trying to set attribute '$attribute' to tag ${tag.tagName} but it was never opened.")

        if (current.tagName.lowercase() != tag.tagName.lowercase()) {
            error("Wrong current tag: current -> ${current.tagName}, given -> ${tag.tagName}.")
        }

        if (value == null) {
            current.removeAttribute(attribute)
            return
        }

        current.setAttribute(attribute, value)
    }

    override fun onTagEvent(tag: HtmlTag, event: String, function: String) {

    }

    override fun onCustomTagStart(tag: CustomHtmlTag) {

    }

    override fun onCustomTagEnd(tag: CustomHtmlTag) {

    }

    override fun createHtmlRenderer(): HtmlRenderer {
        return HtmlDocumentRenderer()
    }

    override fun render(): List<HtmlNode> = elements
}

public interface Component {

    /** Create and return the HTML elements. */
    public fun mount(): List<HtmlNode>
}

public class TestRenderer(private val parent: Component, private val parentElement: HtmlNode) : HtmlDocumentRenderer() {

    override fun onCustomTagStart(tag: CustomHtmlTag) {
        if (tag !is ComponentTag) error("Tried to render unknown custom tag '${tag.tagName}'.")


        // Create the component.
        val component = object : Component {
            override fun mount(): List<HtmlNode> {
                return listOf(
                    HtmlTagNode(
                        "div",
                        false,
                        children = mutableListOf(
                            HtmlContentNode("buh"),
                        ),
                    ),
                )
            }
        }

        // Then mount it.
        val elements = component.mount()
        // Add elements to the current list.
        elements.forEach(this.elements::add)
    }

    override fun createHtmlRenderer(): HtmlRenderer {
        return TestRenderer(parent, parentElement)
    }
}

public inline fun createTestHtml(block: HtmlConsumer.() -> Unit): HtmlDocument {
    return HtmlDocument(
        content = TestRenderer(
            object : Component {
                override fun mount(): List<HtmlNode> {
                    return emptyList()
                }
            },
            HtmlTagNode("root-fucking-tag", false),
        ).apply(block).render().first(),
    )
}

internal class HtmlStringRenderer(
    private val prettyPrint: Boolean = false,
    private val document: HtmlDocument,
) {

    internal fun render(): String = buildString {
        appendElement(0, document.content)
    }

    private fun StringBuilder.appendElement(indentation: Int, node: HtmlNode) {
        val mainIndentation = " ".repeat(indentation)
        val contentIndentation = " ".repeat(indentation + 2)

        if (node !is HtmlTagNode) {
            if (node !is HtmlContentNode) return
            appendLine("$contentIndentation${node.content.escapeHtml()}")
            return
        }

        appendLine(
            buildString {
                append("$mainIndentation<${node.tagName}")
                node.attributes.forEach { (key, value) ->
                    when {
                        // Specific handler for booleans not having `= "true"`.
                        value.isEmpty() -> append(" $key")
                        else -> append(" $key=\"${value.escapeHtml()}\"")
                    }
                }
                append(">")
            },
        )

        if (node.isVoid) return

        node.children.forEach { child ->
            appendElement(indentation + 2, child)
        }

        appendLine("$mainIndentation</${node.tagName}>")
    }
}

@PublishedApi
internal class ComponentTag(
    parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String> = mutableMapOf(),
) : CustomHtmlConsumerTag(parentRenderer) { // TODO, PARENT INSTEAD
    override val tagName: String = "component"
    override val isVoid: Boolean = false
}

@HtmlMarker
public inline fun HtmlConsumer.component() {
    ComponentTag(renderer).visit {}
}

private fun CharSequence.escapeHtml(): String = this.toString()
    .replace("&", "&amp;")
    .replace("<", "&lt;")
    .replace(">", "&gt;")
    .replace("\"", "&quot;")
    .replace("'", "&#39;")
