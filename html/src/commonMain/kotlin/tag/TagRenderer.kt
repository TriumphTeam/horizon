package dev.triumphteam.horizon.html.tag

import dev.triumphteam.horizon.html.visitor.TagVisitor

public expect interface TagRenderer : TagVisitor {
    public fun onTagStart(tag: HtmlTag)
    public fun onTagEnd(tag: HtmlTag)
    public fun onTagContent(tag: HtmlTag, content: CharSequence)
    public fun onAttribute(tag: HtmlTag, attribute: String, value: String?)
}
