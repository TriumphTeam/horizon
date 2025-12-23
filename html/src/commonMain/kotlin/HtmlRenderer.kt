package dev.triumphteam.horizon.html

public expect interface HtmlRenderer : HtmlVisitor {
    public fun onStart(tag: HtmlTag)
    public fun onEnd(tag: HtmlTag)
    public fun onContent(tag: HtmlTag, content: CharSequence)
    public fun onAttribute(tag: HtmlTag, attribute: String, value: String?)
}
