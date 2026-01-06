@file:Suppress("NOTHING_TO_INLINE")

package dev.triumphteam.horizon.html

import kotlinx.browser.document
import org.w3c.dom.Element
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

public interface Tag {

    public val parent: FlowContent
    public val tagName: String
    public val element: Element
}

public abstract class AbstractTag(
    override val parent: FlowContent,
    override val tagName: String,
    initialAttributes: Map<String, String> = emptyMap(),
) : TagAttributeScope() {
    override val element: Element = document.createElement(tagName)

    init {
        initialAttributes.forEach { (key, value) -> element.setAttribute(key, value) }
    }
}

public interface FlowContent {

    public val children: MutableList<Tag>

    public fun appendChild(child: Tag)
}

public abstract class AbstractFlowTag(
    parent: FlowContent,
    tagName: String,
    initialAttributes: Map<String, String> = emptyMap(),
) : AbstractTag(parent, tagName, initialAttributes), FlowContent {

    override val children: MutableList<Tag> = mutableListOf()

    public inline fun text(text: String) {
        element.appendChild(document.createTextNode(text))
    }

    override fun appendChild(child: Tag) {
        children.add(child)
        element.appendChild(child.element)
    }
}

public class FlowContentBuilder : FlowContent {
    override val children: MutableList<Tag> = mutableListOf()
    override fun appendChild(child: Tag) {
        children.add(child)
    }
}

public class SimpleHtmlTag(
    parent: FlowContent,
    tagName: String,
    initialAttributes: Map<String, String> = emptyMap(),
) : AbstractFlowTag(parent, tagName, initialAttributes)

public inline fun createHtml(block: FlowContent.() -> Unit): List<Tag> {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return FlowContentBuilder().apply(block).children
}

@TagDslMarker
public inline fun FlowContent.div(block: AbstractFlowTag.() -> Unit): Tag {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return SimpleHtmlTag(this, "div").apply(block).also {
        appendChild(it)
    }
}
