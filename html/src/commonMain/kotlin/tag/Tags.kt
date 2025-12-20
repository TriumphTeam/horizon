package dev.triumphteam.horizon.html.tag

import dev.triumphteam.horizon.html.tag.attributes.HtmlAttributes
import dev.triumphteam.horizon.html.visitor.TagVisitor
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


@HtmlTagMarker
public inline fun TagVisitor.a(
    id: String? = null,
    className: String? = null,
    href: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: ATag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    ATag(
        renderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
            HtmlAttributes.HREF to href,
        ),
    ).visit(renderer, block)
}

@HtmlTagMarker
public inline fun TagVisitor.abbr(
    id: String? = null,
    className: String? = null,
    title: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    SimpleTag(
        "abbr",
        renderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
            HtmlAttributes.TITLE to title,
        ),
        isInline = false,
        isEmpty = false,
    ).visit(renderer, block)
}

@HtmlTagMarker
public inline fun TagVisitor.address(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    SimpleTag(
        tagName = "address",
        renderer = renderer,
        attributes = attributes.withAttributes(HtmlAttributes.ID to id, HtmlAttributes.CLASS to className),
        isInline = false,
        isEmpty = false,
    ).visit(renderer, block)
}

@HtmlTagMarker
public inline fun TagVisitor.area(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: AreaTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    AreaTag(
        renderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(renderer, block)
}

@HtmlTagMarker
public inline fun TagVisitor.article(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("article", id, className, attributes, block)
}

@HtmlTagMarker
public inline fun TagVisitor.aside(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("aside", id, className, attributes, block)
}

@HtmlTagMarker
public inline fun TagVisitor.audio(
    id: String? = null,
    className: String? = null,
    src: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: AudioTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    AudioTag(
        renderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
            HtmlAttributes.SRC to src,
        ),
    ).visit(renderer, block)
}

@HtmlTagMarker
public inline fun TagVisitor.b(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("b", id, className, attributes, block)
}

@HtmlTagMarker
public inline fun TagVisitor.base(
    id: String? = null,
    className: String? = null,
    href: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: BaseTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    BaseTag(
        renderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
            HtmlAttributes.HREF to href,
        ),
    ).visit(renderer, block)
}

@HtmlTagMarker
public inline fun TagVisitor.bdi(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("bdi", id, className, attributes, block)
}

@HtmlTagMarker
public inline fun TagVisitor.div(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("div", id, className, attributes, block)
}

@PublishedApi
internal inline fun TagVisitor.simpleTag(
    tagName: String,
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    SimpleTag(
        tagName = "div",
        renderer = renderer,
        attributes = attributes.withAttributes(HtmlAttributes.ID to id, HtmlAttributes.CLASS to className),
        isInline = false,
        isEmpty = false,
    ).visit(renderer, block)
}
