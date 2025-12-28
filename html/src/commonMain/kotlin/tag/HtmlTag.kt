@file:Suppress("NOTHING_TO_INLINE")

package dev.triumphteam.horizon.html.tag

import dev.triumphteam.horizon.html.HtmlConsumerTag
import dev.triumphteam.horizon.html.HtmlRenderer
import dev.triumphteam.horizon.html.HtmlScope
import dev.triumphteam.horizon.html.HtmlTag
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
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

public class SimpleTag(
    override val tagName: String,
    parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String>,
    override val isVoid: Boolean,
) : HtmlConsumerTag(parentRenderer)

public class ATag(
    parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlConsumerTag(parentRenderer), HtmlTagWithDownloadAttribute, HtmlTagWithHrefAttribute,
    HtmlTagWithHrefLangAttribute,
    HtmlTagWithMediaAttribute, HtmlTagWithRelAttribute, HtmlTagWithTargetAttribute, HtmlTagWithTypeAttribute,
    HtmlTagWithReferrerPolicyAttribute {

    override val tagName: String = "a"
    override val isVoid: Boolean = false
}

public class AreaTag(
    override val parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlScope(), HtmlTagWithAltAttribute, HtmlTagWithDownloadAttribute, HtmlTagWithHrefAttribute,
    HtmlTagWithHrefLangAttribute, HtmlTagWithMediaAttribute, HtmlTagWithRelAttribute, HtmlTagWithTargetAttribute,
    HtmlTagWithTypeAttribute, HtmlTagWithReferrerPolicyAttribute {

    override val tagName: String = "area"
    override val isVoid: Boolean = true
}

public class AudioTag(
    parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlConsumerTag(parentRenderer), HtmlTagWithAudioVideoAttributes, HtmlTagWithSrcAttribute {

    override val tagName: String = "audio"
    override val isVoid: Boolean = false
}

public class BaseTag(
    override val parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlScope(), HtmlTagWithHrefAttribute, HtmlTagWithTargetAttribute {

    override val tagName: String = "base"
    override val isVoid: Boolean = true
}

public class BlockQuoteTag(
    parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlConsumerTag(parentRenderer) {

    override val tagName: String = "blockquote"
    override val isVoid: Boolean = false
}

public class ButtonTag(
    parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlConsumerTag(parentRenderer), HtmlTagWithAutoFocusAttribute, HtmlTagWithDisabledAttribute,
    HtmlTagWithFormAttribute,
    HtmlTagWithFormActionAttribute, HtmlTagWithTypeAttribute, HtmlTagWithNameAttribute, HtmlTagWithValueAttribute {

    override val tagName: String = "button"
    override val isVoid: Boolean = false

    // TODO: Some extra attributes.
}

public class CanvasTag(
    parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlConsumerTag(parentRenderer), HtmlTagWithWidthAttribute, HtmlTagWithHeightAttribute {

    override val tagName: String = "canvas"
    override val isVoid: Boolean = false
}

public class ImgTag(
    override val parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlScope(), HtmlTagWithAltAttribute, HtmlTagWithHeightAttribute, HtmlTagWithWidthAttribute,
    HtmlTagWithReferrerPolicyAttribute, HtmlTagWithSizesAttribute, HtmlTagWithSrcAttribute {

    override val tagName: String = "img"
    override val isVoid: Boolean = true

    // TODO: Some extra attributes.
}

public class InputTag(
    override val parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlScope(), HtmlTagWithAltAttribute, HtmlTagWithAutocompleteAttribute, HtmlTagWithAutoFocusAttribute,
    HtmlTagWithDisabledAttribute, HtmlTagWithFormAttribute, HtmlTagWithFormActionAttribute, HtmlTagWithHeightAttribute,
    HtmlTagWithWidthAttribute, HtmlTagWithMaxAttribute, HtmlTagWithMinAttribute, HtmlTagWithPlaceholderAttribute,
    HtmlTagWithNameAttribute, HtmlTagWithReadOnlyAttribute, HtmlTagWithRequiredAttribute, HtmlTagWithSizeAttribute,
    HtmlTagWithSrcAttribute, HtmlTagWithTypeAttribute, HtmlTagWithValueAttribute {

    override val tagName: String = "input"
    override val isVoid: Boolean = true

    // TODO: Some extra attributes.
}

public class LabelTag(
    parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlConsumerTag(parentRenderer), HtmlTagWithForAttribute, HtmlTagWithFormAttribute {

    override val tagName: String = "label"
    override val isVoid: Boolean = false
}

public class LiTag(
    parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlConsumerTag(parentRenderer), HtmlTagWithValueAttribute {

    override val tagName: String = "li"
    override val isVoid: Boolean = false
}

public class LinkTag(
    override val parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlScope(), HtmlTagWithHrefAttribute, HtmlTagWithHrefLangAttribute, HtmlTagWithReferrerPolicyAttribute,
    HtmlTagWithRelAttribute, HtmlTagWithSizesAttribute, HtmlTagWithMediaAttribute, HtmlTagWithTypeAttribute,
    HtmlTagWithCrossOriginAttribute {

    override val tagName: String = "link"
    override val isVoid: Boolean = true

    // TODO: Some extra attributes.
}

public class MetaTag(
    override val parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlScope(), HtmlTagWithCharsetAttribute, HtmlTagWithNameAttribute {

    override val tagName: String = "meta"
    override val isVoid: Boolean = true
}

public class OlTag(
    parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlConsumerTag(parentRenderer), HtmlTagWithTypeAttribute {

    override val tagName: String = "ol"
    override val isVoid: Boolean = false
}

public class ScriptTag(
    parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlConsumerTag(parentRenderer), HtmlTagWithCrossOriginAttribute, HtmlTagWithReferrerPolicyAttribute,
    HtmlTagWithSrcAttribute,
    HtmlTagWithTypeAttribute {

    override val tagName: String = "script"
    override val isVoid: Boolean = false
}

public class StyleTag(
    parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlConsumerTag(parentRenderer), HtmlTagWithMediaAttribute, HtmlTagWithTypeAttribute {

    override val tagName: String = "style"
    override val isVoid: Boolean = false
}

public class TextAreaTag(
    parentRenderer: HtmlRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlConsumerTag(parentRenderer), HtmlTagWithAutoFocusAttribute, HtmlTagWithDirnameAttribute,
    HtmlTagWithDisabledAttribute,
    HtmlTagWithFormAttribute, HtmlTagWithPlaceholderAttribute, HtmlTagWithNameAttribute, HtmlTagWithReadOnlyAttribute {

    override val tagName: String = "textarea"
    override val isVoid: Boolean = false

    // TODO: Some extra attributes.
}

public inline fun <T : HtmlTag> T.visit(crossinline block: T.() -> Unit) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    visitTag(block)
}

public inline fun <T : HtmlTag> T.visitTag(block: T.() -> Unit) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    parentRenderer.onStart(this)
    this.block()
    parentRenderer.onEnd(this)
}

@PublishedApi
internal inline fun MutableMap<String, String>.withAttributes(vararg pairs: Pair<String, String?>): MutableMap<String, String> {
    for ((key, value) in pairs) {
        if (value == null) continue
        this[key] = value
    }
    return this
}
