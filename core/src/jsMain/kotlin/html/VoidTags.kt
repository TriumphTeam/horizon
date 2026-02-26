package dev.triumphteam.horizon.html

import dev.triumphteam.horizon.html.attributes.HtmlAttributes
import dev.triumphteam.horizon.html.element.AreaTag
import dev.triumphteam.horizon.html.element.BaseTag
import dev.triumphteam.horizon.html.element.ImgTag
import dev.triumphteam.horizon.html.element.InputTag
import dev.triumphteam.horizon.html.element.LinkTag
import dev.triumphteam.horizon.html.element.MetaTag
import dev.triumphteam.horizon.html.element.VoidTag
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@TagMarker
public inline fun FlowContent.area(
    id: String? = null,
    className: String? = null,
    // TODO: More manual attributes here.
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: AreaTag.() -> Unit,
): Tag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = AreaTag(
            parentComponent = parentComponent,
            initialAttributes = attributes.withAttributes(
                HtmlAttributes.ID to id,
                HtmlAttributes.CLASS to className,
            ),
        ),
        block = block,
    )
}

@TagMarker
public inline fun FlowContent.base(
    id: String? = null,
    className: String? = null,
    href: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: BaseTag.() -> Unit = {},
): BaseTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = BaseTag(
            parentComponent = parentComponent,
            initialAttributes = attributes.withAttributes(
                HtmlAttributes.ID to id,
                HtmlAttributes.CLASS to className,
                HtmlAttributes.HREF to href,
            ),
        ),
        block = block,
    )
}

@TagMarker
public inline fun FlowContent.br(block: TagAttributeScope.() -> Unit = {}): Tag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(VoidTag("br", parentComponent), block)
}

@TagMarker
public inline fun FlowContent.img(
    id: String? = null,
    className: String? = null,
    src: String? = null,
    alt: String? = null,
    draggable: Boolean? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: ImgTag.() -> Unit = {},
): ImgTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = ImgTag(
            parentComponent = parentComponent,
            initialAttributes = attributes.withAttributes(
                HtmlAttributes.ID to id,
                HtmlAttributes.CLASS to className,
                HtmlAttributes.SRC to src,
                HtmlAttributes.ALT to alt,
                HtmlAttributes.DRAGGABLE to draggable?.toString(),
            ),
        ),
        block = block,
    )
}

@TagMarker
public inline fun FlowContent.input(
    id: String? = null,
    className: String? = null,
    type: String? = null,
    checked: Boolean? = null,
    disabled: Boolean? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: InputTag.() -> Unit = {},
): InputTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = InputTag(
            parentComponent = parentComponent,
            initialAttributes = attributes.withAttributes(
                HtmlAttributes.ID to id,
                HtmlAttributes.CLASS to className,
                HtmlAttributes.TYPE to type,
                HtmlAttributes.CHECKED to checked?.toString(),
                HtmlAttributes.DISABLED to disabled?.toString(),
            ),
        ),
        block = block,
    )
}

@TagMarker
public inline fun FlowContent.link(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: LinkTag.() -> Unit = {},
): LinkTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = LinkTag(
            parentComponent = parentComponent,
            initialAttributes = attributes.withAttributes(
                HtmlAttributes.ID to id,
                HtmlAttributes.CLASS to className,
            ),
        ),
        block = block,
    )
}

@TagMarker
public inline fun FlowContent.meta(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: MetaTag.() -> Unit = {},
): MetaTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = MetaTag(
            parentComponent = parentComponent,
            initialAttributes = attributes.withAttributes(
                HtmlAttributes.ID to id,
                HtmlAttributes.CLASS to className,
            ),
        ),
        block = block,
    )
}
