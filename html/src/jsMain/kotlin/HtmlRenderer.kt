@file:Suppress("NOTHING_TO_INLINE")

package dev.triumphteam.horizon.html

import org.w3c.dom.Element
import org.w3c.dom.events.Event

public actual interface HtmlRenderer : HtmlVisitor {
    public actual fun onStart(tag: HtmlTag)
    public actual fun onEnd(tag: HtmlTag)
    public actual fun onContent(tag: HtmlTag, content: CharSequence)
    public actual fun onAttribute(tag: HtmlTag, attribute: String, value: String?)
    public fun onTagEvent(tag: HtmlTag, event: String, function: (Event) -> Unit)
}

public abstract class AbstractDomHtmlRenderer : HtmlRenderer {

    private companion object {
        private val document = kotlinx.browser.document
    }

    override val renderer: HtmlRenderer = this
    private val path = mutableListOf<Element>()

    override fun onStart(tag: HtmlTag) {
        val last = path.lastOrNull()

        if (tag is CustomHtmlTag) {
            onCustomTagStart(tag, last)
            return
        }

        val element = document.createElement(tag.tagName)

        tag.attributes.forEach { (key, value) -> element.setAttribute(key, value) }

        last?.appendChild(element)
        path.add(element)
    }

    override fun onEnd(tag: HtmlTag) {
        if (tag is CustomHtmlTag) {
            onCustomTagEnd(tag)
            return
        }

        val last = last()
        if (last.tagName.lowercase() != tag.tagName.lowercase()) {
            error("We started tag ${tag.tagName} but already trying to end it.")
        }

        if (path.size <= 1) return
        path.removeLast()
    }

    override fun onContent(tag: HtmlTag, content: CharSequence) {
        last().appendChild(document.createTextNode(content.toString()))
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
        function: (Event) -> Unit,
    ) {
        last().setEvent(event, function)
    }

    protected abstract fun onCustomTagStart(tag: CustomHtmlTag, last: Element?)

    protected abstract fun onCustomTagEnd(tag: CustomHtmlTag)

    public fun render(): List<Element> = path

    private fun last(): Element = path.lastOrNull() ?: error("No current tag in the renderer.")
}

private inline fun Element.setEvent(name: String, noinline callback: (Event) -> Unit) {
    asDynamic()[name] = callback
}
