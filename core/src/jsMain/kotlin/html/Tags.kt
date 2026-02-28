package dev.triumphteam.horizon.html

import dev.triumphteam.horizon.html.attributes.HtmlAttributes
import dev.triumphteam.horizon.html.element.ATag
import dev.triumphteam.horizon.html.element.AudioTag
import dev.triumphteam.horizon.html.element.BlockQuoteTag
import dev.triumphteam.horizon.html.element.ButtonTag
import dev.triumphteam.horizon.html.element.CanvasTag
import dev.triumphteam.horizon.html.element.DialogTag
import dev.triumphteam.horizon.html.element.LabelTag
import dev.triumphteam.horizon.html.element.LiTag
import dev.triumphteam.horizon.html.element.OlTag
import dev.triumphteam.horizon.html.element.ScriptTag
import dev.triumphteam.horizon.html.element.SimpleFlowTag
import dev.triumphteam.horizon.html.element.StyleTag
import dev.triumphteam.horizon.html.element.TextAreaTag
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@TagMarker
public inline fun FlowContent.a(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: ATag.() -> Unit = {},
): Tag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = ATag(
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
public inline fun FlowContent.abbr(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "abbr",
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
public inline fun FlowContent.address(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "address",
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
public inline fun FlowContent.article(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "article",
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
public inline fun FlowContent.aside(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "aside",
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
public inline fun FlowContent.audio(
    id: String? = null,
    className: String? = null,
    src: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: AudioTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = AudioTag(
            parentComponent = parentComponent,
            initialAttributes = attributes.withAttributes(
                HtmlAttributes.ID to id,
                HtmlAttributes.CLASS to className,
                HtmlAttributes.SRC to src,
            ),
        ),
        block = block,
    )
}

@TagMarker
public inline fun FlowContent.b(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "b",
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
public inline fun FlowContent.blockquote(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: BlockQuoteTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = BlockQuoteTag(
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
public inline fun FlowContent.body(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "body",
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
public inline fun FlowContent.button(
    id: String? = null,
    className: String? = null,
    popoverTarget: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: ButtonTag.() -> Unit = {},
): Tag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = ButtonTag(
            parentComponent = parentComponent,
            initialAttributes = attributes.withAttributes(
                HtmlAttributes.ID to id,
                HtmlAttributes.CLASS to className,
                HtmlAttributes.POPOVER_TARGET to popoverTarget,
            ),
        ),
        block = block,
    )
}

@TagMarker
public inline fun FlowContent.canvas(
    id: String? = null,
    className: String? = null,
    width: Int? = null,
    height: Int? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: CanvasTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = CanvasTag(
            parentComponent = parentComponent,
            initialAttributes = attributes.withAttributes(
                HtmlAttributes.ID to id,
                HtmlAttributes.CLASS to className,
                HtmlAttributes.WIDTH to width?.toString(),
                HtmlAttributes.HEIGHT to height?.toString(),
            ),
        ),
        block = block,
    )
}

@TagMarker
public inline fun FlowContent.code(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "code",
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
public inline fun FlowContent.del(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "del",
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
public inline fun FlowContent.dialog(
    id: String? = null,
    className: String? = null,
    closedBy: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: DialogTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = DialogTag(
            parentComponent = parentComponent,
            initialAttributes = attributes.withAttributes(
                HtmlAttributes.ID to id,
                HtmlAttributes.CLASS to className,
                HtmlAttributes.CLOSED_BY to closedBy,
            ),
        ),
        block = block,
    )
}

@TagMarker
public inline fun FlowContent.div(
    id: String? = null,
    className: String? = null,
    popover: Boolean? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): Tag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "div",
            parentComponent = parentComponent,
            initialAttributes = attributes.withAttributes(
                HtmlAttributes.ID to id,
                HtmlAttributes.CLASS to className,
                HtmlAttributes.POPOVER to popover?.toString(),
            ),
        ),
        block = block,
    )
}

@TagMarker
public inline fun FlowContent.footer(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "footer",
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
public inline fun FlowContent.h1(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "h1",
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
public inline fun FlowContent.h2(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "h2",
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
public inline fun FlowContent.h3(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "h3",
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
public inline fun FlowContent.h4(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "h4",
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
public inline fun FlowContent.h5(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "h5",
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
public inline fun FlowContent.h6(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "h6",
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
public inline fun FlowContent.head(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "head",
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
public inline fun FlowContent.html(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "html",
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
public inline fun FlowContent.i(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "i",
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
public inline fun FlowContent.label(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: LabelTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = LabelTag(
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
public inline fun FlowContent.li(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: LiTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = LiTag(
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
public inline fun FlowContent.main(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "main",
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
public inline fun FlowContent.nav(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "nav",
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
public inline fun FlowContent.noscript(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "noscript",
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
public inline fun FlowContent.ol(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: OlTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = OlTag(
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
public inline fun FlowContent.p(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "p",
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
public inline fun FlowContent.pre(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "pre",
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
public inline fun FlowContent.s(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "s",
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
public inline fun FlowContent.script(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: ScriptTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = ScriptTag(
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
public inline fun FlowContent.section(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "section",
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
public inline fun FlowContent.small(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "small",
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
public inline fun FlowContent.span(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "span",
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
public inline fun FlowContent.strong(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "strong",
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
public inline fun FlowContent.style(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: StyleTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = StyleTag(
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
public inline fun FlowContent.sub(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "sub",
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
public inline fun FlowContent.sup(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "sup",
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
public inline fun FlowContent.table(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "table",
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
public inline fun FlowContent.tbody(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "tbody",
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
public inline fun FlowContent.textarea(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: TextAreaTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = TextAreaTag(
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
public inline fun FlowContent.tfoot(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "tfoot",
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
public inline fun FlowContent.thead(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "thead",
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
public inline fun FlowContent.title(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "title",
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
public inline fun FlowContent.tr(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "tr",
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
public inline fun FlowContent.u(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "u",
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
public inline fun FlowContent.ul(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "ul",
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
public inline fun FlowContent.varTag(
    id: String? = null,
    className: String? = null,
    attributes: MutableMap<String, String> = mutableMapOf(),
    crossinline block: FlowTag.() -> Unit = {},
): FlowTag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag(
        tag = SimpleFlowTag(
            tagName = "var",
            parentComponent = parentComponent,
            initialAttributes = attributes.withAttributes(
                HtmlAttributes.ID to id,
                HtmlAttributes.CLASS to className,
            ),
        ),
        block = block,
    )
}
