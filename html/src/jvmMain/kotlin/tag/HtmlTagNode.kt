package dev.triumphteam.horizon.html.tag

import dev.triumphteam.horizon.html.HtmlNode

public data class HtmlTagNode(
    val tagName: String,
    val isVoid: Boolean,
    val attributes: MutableMap<String, String> = mutableMapOf(),
    val children: MutableList<HtmlNode> = mutableListOf(),
) : HtmlNode {

    internal fun appendChildren(children: List<HtmlNode>) {
        this.children.addAll(children)
    }

    internal fun removeAttribute(key: String) {
        attributes.remove(key)
    }

    internal fun setAttribute(key: String, value: String) {
        attributes[key] = value
    }
}

internal fun createElement(tagName: String, isVoid: Boolean): HtmlTagNode =
    HtmlTagNode(tagName, isVoid)
