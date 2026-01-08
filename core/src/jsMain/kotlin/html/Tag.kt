@file:Suppress("NOTHING_TO_INLINE")

package dev.triumphteam.horizon.html

import dev.triumphteam.horizon.component.Component
import kotlinx.browser.document
import org.w3c.dom.Element
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@TagMarker
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

@PublishedApi
internal class FlowContentBuilder(
    override val parentComponent: Component,
    override val element: Element,
    private val onTagCreation: (Tag) -> Unit,
) : FlowContent {

    override fun appendChild(child: Tag) {
        onTagCreation(child)
    }
}

@PublishedApi
internal inline fun createHtml(
    parentComponent: Component,
    element: Element,
    renderFunction: FlowContent.() -> Unit,
    noinline onTagCreation: (Tag) -> Unit,
) {
    contract { callsInPlace(renderFunction, InvocationKind.EXACTLY_ONCE) }
    FlowContentBuilder(parentComponent, element, onTagCreation).apply(renderFunction)
}

@PublishedApi
internal inline fun <T : Tag> FlowContent.tag(tag: T, block: T.() -> Unit): T {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return tag.apply(block).also { appendChild(it) }
}

@PublishedApi
internal fun MutableMap<String, String>.withAttributes(vararg pairs: Pair<String, String?>): MutableMap<String, String> {
    for ((key, value) in pairs) {
        if (value == null) continue
        this[key] = value
    }
    return this
}
