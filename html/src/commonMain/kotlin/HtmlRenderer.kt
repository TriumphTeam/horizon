package dev.triumphteam.horizon.html

public expect interface HtmlRenderer : HtmlConsumer {
    public fun onStart(tag: HtmlTag)
    public fun onEnd(tag: HtmlTag)
    public fun onContent(tag: HtmlTag, content: CharSequence)
    public fun onAttribute(tag: HtmlTag, attribute: String, value: String?)
    public fun createHtmlRenderer(): HtmlRenderer
}

public abstract class AbstractHtmlRenderer<T> : HtmlRenderer {

    override val renderer: HtmlRenderer = this
    override val parentRenderer: HtmlRenderer = this

    protected val elements: MutableList<T> = mutableListOf()
    protected var current: T? = null

    override fun onStart(tag: HtmlTag) {
        if (tag is CustomHtmlTag) {
            onCustomTagStart(tag)
            return
        }

        onTagStart(tag)
    }

    override fun onEnd(tag: HtmlTag) {
        if (tag is CustomHtmlTag) {
            onCustomTagEnd(tag)
            return
        }

        onTagEnd(tag)
    }

    protected abstract fun onTagStart(tag: HtmlTag)

    protected abstract fun onTagEnd(tag: HtmlTag)

    protected abstract fun onCustomTagStart(tag: CustomHtmlTag)

    protected abstract fun onCustomTagEnd(tag: CustomHtmlTag)
}
