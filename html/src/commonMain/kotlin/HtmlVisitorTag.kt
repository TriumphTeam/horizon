package dev.triumphteam.horizon.html

public abstract class HtmlVisitorTag : HtmlScope(), HtmlVisitor {

    public fun text(text: String) {
        renderer.onContent(this, text)
    }
}
