package dev.triumphteam.horizon.html.tag

internal data class HtmlTagElement(
    val tagName: String,
    val isVoid: Boolean,
    val content: MutableMap<Int, MutableList<CharSequence>> = mutableMapOf(),
    val attributes: MutableMap<String, String> = mutableMapOf(),
    val children: MutableList<HtmlTagElement> = mutableListOf(),
) {

    fun setContent(content: CharSequence) {
        this.content.getOrPut(children.size - 1) { mutableListOf() }.add(content)
    }

    fun appendChild(child: HtmlTagElement) {
        children.add(child)
    }

    fun removeAttribute(key: String) {
        attributes.remove(key)
    }

    fun setAttribute(key: String, value: String) {
        attributes[key] = value
    }
}

internal fun createElement(tagName: String, isVoid: Boolean): HtmlTagElement =
    HtmlTagElement(tagName, isVoid)
