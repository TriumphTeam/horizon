package dev.triumphteam.horizon.html

public abstract class HtmlConsumerTag(override val parentRenderer: HtmlRenderer) : HtmlScope(), HtmlConsumer {

    override val renderer: HtmlRenderer = parentRenderer.createHtmlRenderer()

    public fun text(text: String) {
        renderer.onContent(this, text)
    }
}

public abstract class CustomHtmlConsumerTag(parentRenderer: HtmlRenderer) : HtmlConsumerTag(parentRenderer),
    CustomHtmlTag

