@file:Suppress("NOTHING_TO_INLINE")

package dev.triumphteam.horizon.html

import org.w3c.dom.Element
import org.w3c.dom.events.Event

public actual interface HtmlRenderer : HtmlConsumer {
    public actual fun onStart(tag: HtmlTag)
    public actual fun onEnd(tag: HtmlTag)
    public actual fun onContent(tag: HtmlTag, content: CharSequence)
    public actual fun onAttribute(tag: HtmlTag, attribute: String, value: String?)
    public actual fun createHtmlRenderer(): HtmlRenderer
    public fun onTagEvent(tag: HtmlTag, event: String, function: (Event) -> Unit)

    public fun render(): List<Element>
}

public abstract class AbstractDomHtmlRenderer : AbstractHtmlRenderer<Element>() {

    private companion object {
        private val document = kotlinx.browser.document
    }

    override fun onTagStart(tag: HtmlTag) {
        val element = document.createElement(tag.tagName)
        tag.attributes.forEach { (key, value) -> element.setAttribute(key, value) }
        current = element
    }

    override fun onTagEnd(tag: HtmlTag) {
        val current = current ?: error("Trying to end tag ${tag.tagName} but it was never opened.")

        if (current.tagName.lowercase() != tag.tagName.lowercase()) {
            error("Tried to end tag ${tag.tagName} but ${current.tagName} is the current open.")
        }

        if (tag is HtmlConsumerTag) {
            tag.renderer.render().forEach(current::appendChild)
        }

        elements.add(current)
    }

    override fun onCustomTagStart(tag: CustomHtmlTag) {

    }

    override fun onCustomTagEnd(tag: CustomHtmlTag) {

    }

    override fun onContent(tag: HtmlTag, content: CharSequence) {
        val current = current
            ?: error("Trying to set content on tag ${tag.tagName} but it was never opened.")
        current.appendChild(document.createTextNode(content.toString()))
    }

    override fun onAttribute(
        tag: HtmlTag,
        attribute: String,
        value: String?,
    ) {
        val current = current
            ?: error("Trying to set attribute on tag ${tag.tagName} but it was never opened.")

        if (current.tagName.lowercase() != tag.tagName.lowercase()) {
            error("Wrong current tag: current -> ${current.tagName}, given -> ${tag.tagName}.")
        }

        if (value == null) {
            current.removeAttribute(attribute)
            return
        }

        current.setAttribute(attribute, value)
    }

    override fun onTagEvent(
        tag: HtmlTag,
        event: String,
        function: (Event) -> Unit,
    ) {
        val current = current ?: error("Trying to set event on tag ${tag.tagName} but it was never opened.")
        current.setEvent(event, function)
    }

    override fun render(): List<Element> = elements
}

public abstract class OldAbstractDomHtmlRenderer : HtmlRenderer {

    private companion object {
        private val document = kotlinx.browser.document
    }

    override val renderer: HtmlRenderer = this
    protected val path: MutableList<Element> = mutableListOf()

    // TODO REWRITE THIS BITCH
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

    // public fun render(): List<Element> = path

    private fun last(): Element = path.lastOrNull() ?: error("No current tag in the renderer.")
}

private inline fun Element.setEvent(name: String, noinline callback: (Event) -> Unit) {
    asDynamic()[name] = callback
}
