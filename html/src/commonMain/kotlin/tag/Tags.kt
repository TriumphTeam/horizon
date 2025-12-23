package dev.triumphteam.horizon.html.tag

import dev.triumphteam.horizon.html.HtmlVisitor
import dev.triumphteam.horizon.html.HtmlVisitorTag
import dev.triumphteam.horizon.html.tag.attributes.HtmlAttributes
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


@HtmlMarker
public inline fun HtmlVisitor.a(
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

@HtmlMarker
public inline fun HtmlVisitor.abbr(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("abbr", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.address(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("address", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.area(
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

@HtmlMarker
public inline fun HtmlVisitor.article(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("article", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.aside(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("aside", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.audio(
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

@HtmlMarker
public inline fun HtmlVisitor.b(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("b", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.base(
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

@HtmlMarker
public inline fun HtmlVisitor.blockquote(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: BlockQuoteTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    BlockQuoteTag(
        renderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(renderer, block)
}

@HtmlMarker
public inline fun HtmlVisitor.body(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("body", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.br(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("br", id, className, attributes, isVoid = true, block)
}

@HtmlMarker
public inline fun HtmlVisitor.button(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: ButtonTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    ButtonTag(
        renderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(renderer, block)
}

@HtmlMarker
public inline fun HtmlVisitor.canvas(
    id: String? = null,
    className: String? = null,
    width: Int? = null,
    height: Int? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: CanvasTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    CanvasTag(
        renderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
            HtmlAttributes.WIDTH to width?.toString(),
            HtmlAttributes.HEIGHT to height?.toString(),
        ),
    ).visit(renderer, block)
}

@HtmlMarker
public inline fun HtmlVisitor.code(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("code", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.del(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("del", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.div(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("div", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.footer(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("footer", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.h1(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("h1", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.h2(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("h2", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.h3(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("h3", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.h4(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("h4", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.h5(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("h5", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.h6(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("h6", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.head(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("head", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.html(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("html", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.i(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("i", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.img(
    id: String? = null,
    className: String? = null,
    src: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    @HtmlMarker crossinline block: ImgTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    ImgTag(
        renderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
            HtmlAttributes.SRC to src,
        ),
    ).visit(renderer, block)
}

@HtmlMarker
public inline fun HtmlVisitor.input(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: InputTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    InputTag(
        renderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(renderer, block)
}

@HtmlMarker
public inline fun HtmlVisitor.label(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: LabelTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    LabelTag(
        renderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(renderer, block)
}

@HtmlMarker
public inline fun HtmlVisitor.li(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: LiTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    LiTag(
        renderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(renderer, block)
}

@HtmlMarker
public inline fun HtmlVisitor.link(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: LinkTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    LinkTag(
        renderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(renderer, block)
}

@HtmlMarker
public inline fun HtmlVisitor.main(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("main", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.meta(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: MetaTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    MetaTag(
        renderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(renderer, block)
}

@HtmlMarker
public inline fun HtmlVisitor.nav(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("nav", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.noscript(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("noscript", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.ol(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: OlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    OlTag(
        renderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(renderer, block)
}

@HtmlMarker
public inline fun HtmlVisitor.p(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("p", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.pre(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("pre", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.s(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("s", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.script(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: ScriptTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    ScriptTag(
        renderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(renderer, block)
}

@HtmlMarker
public inline fun HtmlVisitor.section(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("section", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.small(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("small", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.span(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("span", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.strong(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("strong", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.style(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: StyleTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    StyleTag(
        renderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(renderer, block)
}

@HtmlMarker
public inline fun HtmlVisitor.sub(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("sub", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.sup(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("sup", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.table(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("table", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.tbody(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("tbody", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.textarea(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: TextAreaTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    TextAreaTag(
        renderer = renderer,
        attributes = attributes.withAttributes(
            HtmlAttributes.ID to id,
            HtmlAttributes.CLASS to className,
        ),
    ).visit(renderer, block)
}

@HtmlMarker
public inline fun HtmlVisitor.tfoot(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("tfoot", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.thead(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("thead", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.title(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("title", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.tr(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("tr", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.u(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("u", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.ul(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("ul", id, className, attributes, isVoid = false, block)
}

@HtmlMarker
public inline fun HtmlVisitor.varTag(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("var", id, className, attributes, isVoid = false, block)
}

@PublishedApi
internal inline fun HtmlVisitor.simpleTag(
    tagName: String,
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    isVoid: Boolean = false,
    crossinline block: HtmlVisitorTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    SimpleTag(
        tagName = tagName,
        renderer = renderer,
        attributes = attributes.withAttributes(HtmlAttributes.ID to id, HtmlAttributes.CLASS to className),
        isVoid = isVoid,
    ).visit(renderer, block)
}
