package dev.triumphteam.horizon.html

import org.w3c.dom.events.Event

public actual interface HtmlRenderer : HtmlVisitor {
    public actual fun onStart(tag: HtmlTag)
    public actual fun onEnd(tag: HtmlTag)
    public actual fun onContent(tag: HtmlTag, content: CharSequence)
    public actual fun onAttribute(tag: HtmlTag, attribute: String, value: String?)
    public fun onTagEvent(tag: HtmlTag, event: String, function: (Event) -> Unit)
}
