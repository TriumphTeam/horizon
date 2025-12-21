package dev.triumphteam.horizon.html

import dev.triumphteam.horizon.html.tag.HtmlTag

public expect interface TagRenderer : TagVisitor {
    public fun onStart(tag: HtmlTag)
    public fun onEnd(tag: HtmlTag)
    public fun onContent(tag: HtmlTag, content: CharSequence)
    public fun onAttribute(tag: HtmlTag, attribute: String, value: String?)
}
