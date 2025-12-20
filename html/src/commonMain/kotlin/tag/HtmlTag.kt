@file:Suppress("NOTHING_TO_INLINE")

package dev.triumphteam.horizon.html.tag

import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithAltAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithAudioVideoAttributes
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithDownloadAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithHrefAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithHrefLangAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithMediaAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithReferrerPolicyAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithRelAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithSrcAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithTargetAttribute
import dev.triumphteam.horizon.html.tag.attributes.HtmlTagWithTypeAttribute
import dev.triumphteam.horizon.html.visitor.TagVisitor
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

public interface HtmlTag : TagVisitor {
    public val tagName: String
    public val isInline: Boolean
    public val isEmpty: Boolean

    public val attributes: MutableMap<String, String>

    public fun text(text: String) {
        renderer.onTagContent(this, text)
    }
}

public data class SimpleTag(
    override val tagName: String,
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
    override val isInline: Boolean,
    override val isEmpty: Boolean,
) : HtmlTag

public data class ATag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithDownloadAttribute, HtmlTagWithHrefAttribute, HtmlTagWithHrefLangAttribute, HtmlTagWithMediaAttribute,
    HtmlTagWithRelAttribute, HtmlTagWithTargetAttribute, HtmlTagWithTypeAttribute, HtmlTagWithReferrerPolicyAttribute {

    override val tagName: String = "a"
    override val isInline: Boolean = false
    override val isEmpty: Boolean = false
}

public data class AreaTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithAltAttribute, HtmlTagWithDownloadAttribute, HtmlTagWithHrefAttribute, HtmlTagWithHrefLangAttribute,
    HtmlTagWithMediaAttribute, HtmlTagWithRelAttribute, HtmlTagWithTargetAttribute, HtmlTagWithTypeAttribute,
    HtmlTagWithReferrerPolicyAttribute {

    override val tagName: String = "area"
    override val isInline: Boolean = false
    override val isEmpty: Boolean = false
}

public data class AudioTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithAudioVideoAttributes, HtmlTagWithSrcAttribute {

    override val tagName: String = "audio"
    override val isInline: Boolean = false
    override val isEmpty: Boolean = false
}

public data class BaseTag(
    override val renderer: TagRenderer,
    override val attributes: MutableMap<String, String>,
) : HtmlTagWithHrefAttribute, HtmlTagWithTargetAttribute {

    override val tagName: String = "base"
    override val isInline: Boolean = false
    override val isEmpty: Boolean = false
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
    renderer.onTagStart(this)
    this.block()
    renderer.onTagEnd(this)
}

@PublishedApi
internal inline fun MutableMap<String, String>.withAttributes(vararg pairs: Pair<String, String?>): MutableMap<String, String> {
    for ((key, value) in pairs) {
        if (value == null) continue
        this[key] = value
    }
    return this
}
