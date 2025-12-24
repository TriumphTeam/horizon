package dev.triumphteam.horizon.html

public interface HtmlRenderable {
    public val renderer: HtmlRenderer
}

public interface HtmlTag : HtmlRenderable {
    public val tagName: String
    public val isVoid: Boolean
    public val attributes: MutableMap<String, String>
}

public interface CustomHtmlTag : HtmlTag
