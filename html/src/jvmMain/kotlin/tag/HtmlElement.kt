package dev.triumphteam.horizon.html.tag

public data class HtmlElement(
    val tagName: String,
    val isVoid: Boolean,
    val content: MutableMap<Int, MutableList<CharSequence>> = mutableMapOf(),
    val attributes: MutableMap<String, String> = mutableMapOf(),
    val children: MutableList<HtmlElement> = mutableListOf(),
) {

    internal fun setContent(content: CharSequence) {
        this.content.getOrPut(children.size - 1) { mutableListOf() }.add(content)
    }

    internal fun appendChild(child: HtmlElement) {
        children.add(child)
    }

    internal fun removeAttribute(key: String) {
        attributes.remove(key)
    }

    internal fun setAttribute(key: String, value: String) {
        attributes[key] = value
    }
}

internal fun createElement(tagName: String, isVoid: Boolean): HtmlElement =
    HtmlElement(tagName, isVoid)
