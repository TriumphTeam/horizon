@file:Suppress("NOTHING_TO_INLINE")

package dev.triumphteam.horizon.html.tag

import dev.triumphteam.horizon.html.TagRenderer
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithAltAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithAudioVideoAttributes
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithAutoFocusAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithAutocompleteAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithCharsetAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithCrossOriginAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithDirnameAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithDisabledAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithDownloadAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithForAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithFormActionAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithFormAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithHeightAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithHrefAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithHrefLangAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithMaxAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithMediaAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithMinAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithNameAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithPlaceholderAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithReadOnlyAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithReferrerPolicyAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithRelAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithRequiredAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithSizeAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithSizesAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithSrcAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithTargetAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithTypeAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithValueAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithWidthAttribute
import dev.triumphteam.horizon.html.TagVisitor
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

public interface HtmlTag : TagVisitor {
    public val tagName: String
    public val isVoid: Boolean

    public val attributes: MutableMap<String, String>

    public fun text(text: String) {
        renderer.onContent(this, text)
    }
}

public data class SimpleTag(
    override val tagName: String,
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
    override val isVoid: Boolean,
) : HtmlTag

public data class ATag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithDownloadAttribute, HtmlTagWithHrefAttribute, HtmlTagWithHrefLangAttribute, HtmlTagWithMediaAttribute,
    HtmlTagWithRelAttribute, HtmlTagWithTargetAttribute, HtmlTagWithTypeAttribute, HtmlTagWithReferrerPolicyAttribute {

    override val tagName: String = "a"
    override val isVoid: Boolean = false
}

public data class AreaTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithAltAttribute, HtmlTagWithDownloadAttribute, HtmlTagWithHrefAttribute, HtmlTagWithHrefLangAttribute,
    HtmlTagWithMediaAttribute, HtmlTagWithRelAttribute, HtmlTagWithTargetAttribute, HtmlTagWithTypeAttribute,
    HtmlTagWithReferrerPolicyAttribute {

    override val tagName: String = "area"
    override val isVoid: Boolean = true
}

public data class AudioTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithAudioVideoAttributes, HtmlTagWithSrcAttribute {

    override val tagName: String = "audio"
    override val isVoid: Boolean = false
}

public data class BaseTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithHrefAttribute, HtmlTagWithTargetAttribute {

    override val tagName: String = "base"
    override val isVoid: Boolean = true
}

public data class BlockQuoteTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTag {

    override val tagName: String = "blockquote"
    override val isVoid: Boolean = false
}

public data class ButtonTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithAutoFocusAttribute, HtmlTagWithDisabledAttribute, HtmlTagWithFormAttribute,
    HtmlTagWithFormActionAttribute, HtmlTagWithTypeAttribute, HtmlTagWithNameAttribute, HtmlTagWithValueAttribute {

    override val tagName: String = "button"
    override val isVoid: Boolean = false

    // TODO: Some extra attributes.
}

public data class CanvasTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithWidthAttribute, HtmlTagWithHeightAttribute {

    override val tagName: String = "canvas"
    override val isVoid: Boolean = false
}

public data class ImgTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithAltAttribute, HtmlTagWithHeightAttribute, HtmlTagWithWidthAttribute, HtmlTagWithReferrerPolicyAttribute,
    HtmlTagWithSizesAttribute, HtmlTagWithSrcAttribute {

    override val tagName: String = "img"
    override val isVoid: Boolean = true

    // TODO: Some extra attributes.
}

public data class InputTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithAltAttribute, HtmlTagWithAutocompleteAttribute, HtmlTagWithAutoFocusAttribute,
    HtmlTagWithDisabledAttribute, HtmlTagWithFormAttribute, HtmlTagWithFormActionAttribute, HtmlTagWithHeightAttribute,
    HtmlTagWithWidthAttribute, HtmlTagWithMaxAttribute, HtmlTagWithMinAttribute, HtmlTagWithPlaceholderAttribute,
    HtmlTagWithNameAttribute, HtmlTagWithReadOnlyAttribute, HtmlTagWithRequiredAttribute, HtmlTagWithSizeAttribute,
    HtmlTagWithSrcAttribute, HtmlTagWithTypeAttribute, HtmlTagWithValueAttribute {

    override val tagName: String = "input"
    override val isVoid: Boolean = true

    // TODO: Some extra attributes.
}

public data class LabelTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithForAttribute, HtmlTagWithFormAttribute {

    override val tagName: String = "label"
    override val isVoid: Boolean = false
}

public data class LiTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithValueAttribute {

    override val tagName: String = "li"
    override val isVoid: Boolean = false
}

public data class LinkTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithHrefAttribute, HtmlTagWithHrefLangAttribute, HtmlTagWithReferrerPolicyAttribute, HtmlTagWithRelAttribute,
    HtmlTagWithSizesAttribute, HtmlTagWithMediaAttribute, HtmlTagWithTypeAttribute, HtmlTagWithCrossOriginAttribute {

    override val tagName: String = "link"
    override val isVoid: Boolean = true

    // TODO: Some extra attributes.
}

public data class MetaTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithCharsetAttribute, HtmlTagWithNameAttribute {

    override val tagName: String = "meta"
    override val isVoid: Boolean = true
}

public data class OlTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithTypeAttribute {

    override val tagName: String = "ol"
    override val isVoid: Boolean = false
}

public data class ScriptTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithCrossOriginAttribute, HtmlTagWithReferrerPolicyAttribute, HtmlTagWithSrcAttribute,
    HtmlTagWithTypeAttribute {

    override val tagName: String = "script"
    override val isVoid: Boolean = false
}

public data class StyleTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithMediaAttribute, HtmlTagWithTypeAttribute {

    override val tagName: String = "style"
    override val isVoid: Boolean = false
}

public data class TextAreaTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithAutoFocusAttribute, HtmlTagWithDirnameAttribute, HtmlTagWithDisabledAttribute, HtmlTagWithFormAttribute,
    HtmlTagWithPlaceholderAttribute, HtmlTagWithNameAttribute, HtmlTagWithReadOnlyAttribute {

    override val tagName: String = "textarea"
    override val isVoid: Boolean = false

    // TODO: Some extra attributes.
}

public inline fun <T : HtmlTag> T.visit(crossinline block: T.() -> Unit) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    visitTag(block)
}

public inline fun <T : HtmlTag> T.visit(renderer: TagRenderer, crossinline block: T.() -> Unit) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    require(this.renderer === renderer) { "Wrong renderer!" }
    visitTag(block)
}

public inline fun <T : HtmlTag> T.visitTag(block: T.() -> Unit) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    renderer.onStart(this)
    this.block()
    renderer.onEnd(this)
}

@PublishedApi
internal inline fun MutableMap<String, String>.withAttributes(vararg pairs: Pair<String, String?>): MutableMap<String, String> {
    for ((key, value) in pairs) {
        if (value == null) continue
        this[key] = value
    }
    return this
}
