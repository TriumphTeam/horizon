package dev.triumphteam.horizon.html.tag

import dev.triumphteam.horizon.html.HtmlConsumer
import dev.triumphteam.horizon.html.HtmlConsumerTag
import dev.triumphteam.horizon.html.tag.attributes.HtmlAttributes
import dev.triumphteam.horizon.html.tag.withAttributes
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@HtmlMarker
public inline fun HtmlConsumer.abbr(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("abbr", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.address(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("address", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.article(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("article", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.aside(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("aside", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.audio(
    id: String? = null,
    className: String? = null,
    src: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: AudioTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    AudioTag(
        parentRenderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
            HtmlAttributes.SRC to src,
        ),
    ).visit(block)
}

@HtmlMarker
public inline fun HtmlConsumer.b(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("b", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.base(
    id: String? = null,
    className: String? = null,
    href: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: BaseTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    BaseTag(
        parentRenderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
            HtmlAttributes.HREF to href,
        ),
    ).visit(block)
}

@HtmlMarker
public inline fun HtmlConsumer.blockquote(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: BlockQuoteTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    BlockQuoteTag(
        parentRenderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(block)
}

@HtmlMarker
public inline fun HtmlConsumer.body(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("body", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.br(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("br", id, className, attributes, isVoid = true, block)
}

@HtmlMarker
public inline fun HtmlConsumer.button(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: ButtonTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    ButtonTag(
        parentRenderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(block)
}

@HtmlMarker
public inline fun HtmlConsumer.canvas(
    id: String? = null,
    className: String? = null,
    width: Int? = null,
    height: Int? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: CanvasTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    CanvasTag(
        parentRenderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
            HtmlAttributes.WIDTH to width?.toString(),
            HtmlAttributes.HEIGHT to height?.toString(),
        ),
    ).visit(block)
}

@HtmlMarker
public inline fun HtmlConsumer.code(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("code", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.del(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("del", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.div(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("div", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.footer(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("footer", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.h1(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("h1", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.h2(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("h2", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.h3(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("h3", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.h4(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("h4", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.h5(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("h5", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.h6(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("h6", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.head(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("head", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.html(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("html", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.i(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("i", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.label(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: LabelTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    LabelTag(
        parentRenderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(block)
}

@HtmlMarker
public inline fun HtmlConsumer.li(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: LiTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    LiTag(
        parentRenderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(block)
}

@HtmlMarker
public inline fun HtmlConsumer.link(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: LinkTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    LinkTag(
        parentRenderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(block)
}

@HtmlMarker
public inline fun HtmlConsumer.main(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("main", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.meta(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: MetaTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    MetaTag(
        parentRenderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(block)
}

@HtmlMarker
public inline fun HtmlConsumer.nav(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("nav", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.noscript(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("noscript", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.ol(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: OlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    OlTag(
        parentRenderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(block)
}

@HtmlMarker
public inline fun HtmlConsumer.p(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("p", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.pre(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("pre", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.s(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("s", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.script(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: ScriptTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    ScriptTag(
        parentRenderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(block)
}

@HtmlMarker
public inline fun HtmlConsumer.section(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("section", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.small(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("small", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.span(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("span", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.strong(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("strong", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.style(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: StyleTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    StyleTag(
        parentRenderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(block)
}

@HtmlMarker
public inline fun HtmlConsumer.sub(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("sub", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.sup(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("sup", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.table(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("table", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.tbody(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("tbody", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.textarea(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: TextAreaTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    TextAreaTag(
        parentRenderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(block)
}

@HtmlMarker
public inline fun HtmlConsumer.tfoot(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("tfoot", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.thead(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("thead", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.title(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("title", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.tr(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("tr", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.u(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("u", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.ul(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("ul", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlConsumer.varTag(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("var", id, className, attributes, isVoid = false, block)
}

@PublishedApi
internal inline fun HtmlConsumer.simpleTag(
    tagName: String,
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    isVoid: Boolean = false,
    crossinline block: HtmlConsumerTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    SimpleTag(
        tagName = tagName,
        parentRenderer = renderer,
        attributes = attributes.withAttributes(HtmlAttributes.ID to id, HtmlAttributes.CLASS to className),
        isVoid = isVoid,
    ).visit(block)
}
