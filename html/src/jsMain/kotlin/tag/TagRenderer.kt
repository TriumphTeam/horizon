package dev.triumphteam.horizon.html.tag

import dev.triumphteam.horizon.html.visitor.TagVisitor
import org.w3c.dom.events.Event

public actual interface TagRenderer : TagVisitor {
    public actual fun onTagStart(tag: HtmlTag)
    public actual fun onTagEnd(tag: HtmlTag)
    public actual fun onTagContent(tag: HtmlTag, content: CharSequence)
    public fun onTagEvent(tag: HtmlTag, event: String, function: (Event) -> Unit)
}
