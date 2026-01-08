@file:Suppress("NOTHING_TO_INLINE")

package dev.triumphteam.horizon.html

import dev.triumphteam.horizon.component.Component
import dev.triumphteam.horizon.html.attributes.TagWithAutoFocusAttribute
import dev.triumphteam.horizon.html.attributes.TagWithDisabledAttribute
import dev.triumphteam.horizon.html.attributes.TagWithDownloadAttribute
import dev.triumphteam.horizon.html.attributes.TagWithFormActionAttribute
import dev.triumphteam.horizon.html.attributes.TagWithFormAttribute
import dev.triumphteam.horizon.html.attributes.TagWithHrefAttribute
import dev.triumphteam.horizon.html.attributes.TagWithHrefLangAttribute
import dev.triumphteam.horizon.html.attributes.TagWithMediaAttribute
import dev.triumphteam.horizon.html.attributes.TagWithNameAttribute
import dev.triumphteam.horizon.html.attributes.TagWithReferrerPolicyAttribute
import dev.triumphteam.horizon.html.attributes.TagWithRelAttribute
import dev.triumphteam.horizon.html.attributes.TagWithTargetAttribute
import dev.triumphteam.horizon.html.attributes.TagWithTypeAttribute
import dev.triumphteam.horizon.html.attributes.TagWithValueAttribute
import kotlinx.browser.document
import org.w3c.dom.Element
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

public interface FlowChild {
    public val parentComponent: Component
    public val element: Element
}

public interface Tag : FlowChild {
    public val tagName: String
}

public abstract class AbstractTag(
    override val tagName: String,
    override val parentComponent: Component,
    initialAttributes: Map<String, String> = emptyMap(),
) : TagAttributeScope() {
    override val element: Element = document.createElement(tagName)

    init {
        initialAttributes.forEach { (key, value) -> element.setAttribute(key, value) }
    }
}

public interface FlowContent : FlowChild {

    public fun appendChild(child: Tag)
}

public abstract class FlowTag(
    tagName: String,
    parentComponent: Component,
    initialAttributes: Map<String, String> = emptyMap(),
) : AbstractTag(tagName, parentComponent, initialAttributes), FlowContent {

    public inline fun text(text: String) {
        element.appendChild(document.createTextNode(text))
    }

    override fun appendChild(child: Tag) {
        element.appendChild(child.element)
    }
}

public class FlowContentBuilder(
    override val parentComponent: Component,
    override val element: Element,
    private val onTagCreation: (Tag) -> Unit,
) : FlowContent {

    override fun appendChild(child: Tag) {
        onTagCreation(child)
    }
}

public class SimpleTag(
    tagName: String,
    parentComponent: Component,
    initialAttributes: Map<String, String> = emptyMap(),
) : AbstractTag(tagName, parentComponent, initialAttributes)

public class SimpleFlowTag(
    tagName: String,
    parentComponent: Component,
    initialAttributes: Map<String, String> = emptyMap(),
) : FlowTag(tagName, parentComponent, initialAttributes)

public class ATag(
    parentComponent: Component,
    initialAttributes: Map<String, String> = emptyMap(),
) : FlowTag("a", parentComponent, initialAttributes), TagWithDownloadAttribute, TagWithHrefAttribute,
    TagWithHrefLangAttribute, TagWithMediaAttribute, TagWithRelAttribute, TagWithTargetAttribute, TagWithTypeAttribute,
    TagWithReferrerPolicyAttribute

public class ButtonTag(
    parentComponent: Component,
    initialAttributes: Map<String, String> = emptyMap(),
) : FlowTag("button", parentComponent, initialAttributes), TagWithAutoFocusAttribute, TagWithDisabledAttribute,
    TagWithFormAttribute, TagWithFormActionAttribute, TagWithTypeAttribute, TagWithNameAttribute,
    TagWithValueAttribute {

    // TODO: Some extra attributes.
}

public inline fun createHtml(
    parentComponent: Component,
    element: Element,
    block: FlowContent.() -> Unit,
    noinline onTagCreation: (Tag) -> Unit,
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    FlowContentBuilder(parentComponent, element, onTagCreation).apply(block)
}

@TagMarker
public inline fun FlowContent.div(id: String? = null, block: FlowTag.() -> Unit): Tag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return SimpleFlowTag("div", parentComponent).apply(block).also {
        appendChild(it)
    }
}

@TagMarker
public inline fun FlowContent.a(block: ATag.() -> Unit): Tag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return ATag(parentComponent).apply(block).also {
        appendChild(it)
    }
}

@TagMarker
public inline fun FlowContent.button(block: ButtonTag.() -> Unit): Tag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return ButtonTag(parentComponent).apply(block).also {
        appendChild(it)
    }
}

@TagMarker
public inline fun FlowContent.br(): Tag {
    return SimpleFlowTag("br", parentComponent).also {
        appendChild(it)
    }
}
