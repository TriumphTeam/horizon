package dev.triumphteam.horizon.html.tag

import dev.triumphteam.horizon.html.TagVisitor
import dev.triumphteam.horizon.html.tag.attributes.HtmlAttributes
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
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("abbr", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.address(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("address", id, className, attributes, isVoid = false, block)
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
    simpleTag("article", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.aside(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("aside", id, className, attributes, isVoid = false, block)
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
    simpleTag("b", id, className, attributes, isVoid = false, block)
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
public inline fun TagVisitor.blockquote(
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

@HtmlTagMarker
public inline fun TagVisitor.body(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("body", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.br(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("br", id, className, attributes, isVoid = true, block)
}

@HtmlTagMarker
public inline fun TagVisitor.button(
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

@HtmlTagMarker
public inline fun TagVisitor.canvas(
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

@HtmlTagMarker
public inline fun TagVisitor.code(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("code", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.del(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("del", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.div(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("div", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.footer(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("footer", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.h1(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("h1", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.h2(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("h2", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.h3(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("h3", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.h4(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("h4", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.h5(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("h5", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.h6(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("h6", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.head(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("head", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.html(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("html", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.i(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("i", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.img(
    id: String? = null,
    className: String? = null,
    src: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: ImgTag.() -> Unit = {},
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

@HtmlTagMarker
public inline fun TagVisitor.input(
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

@HtmlTagMarker
public inline fun TagVisitor.label(
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

@HtmlTagMarker
public inline fun TagVisitor.li(
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

@HtmlTagMarker
public inline fun TagVisitor.link(
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

@HtmlTagMarker
public inline fun TagVisitor.main(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("main", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.meta(
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

@HtmlTagMarker
public inline fun TagVisitor.nav(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("nav", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.noscript(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("noscript", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.ol(
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

@HtmlTagMarker
public inline fun TagVisitor.p(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("p", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.pre(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("pre", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.s(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("s", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.script(
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

@HtmlTagMarker
public inline fun TagVisitor.section(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("section", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.small(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("small", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.span(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("span", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.strong(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("strong", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.style(
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

@HtmlTagMarker
public inline fun TagVisitor.sub(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("sub", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.sup(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("sup", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.table(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("table", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.tbody(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("tbody", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.textarea(
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

@HtmlTagMarker
public inline fun TagVisitor.tfoot(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("tfoot", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.thead(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("thead", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.title(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("title", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.tr(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("tr", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.u(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("u", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.ul(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("ul", id, className, attributes, isVoid = false, block)
}

@HtmlTagMarker
public inline fun TagVisitor.varTag(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    simpleTag("var", id, className, attributes, isVoid = false, block)
}

@PublishedApi
internal inline fun TagVisitor.simpleTag(
    tagName: String,
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    isVoid: Boolean = false,
    crossinline block: HtmlTag.() -> Unit = {},
) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    SimpleTag(
        tagName = tagName,
        renderer = renderer,
        attributes = attributes.withAttributes(HtmlAttributes.ID to id, HtmlAttributes.CLASS to className),
        isVoid = isVoid,
    ).visit(renderer, block)
}
