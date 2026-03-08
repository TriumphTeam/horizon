@file:Suppress("NOTHING_TO_INLINE")

package dev.triumphteam.horizon.html

import dev.triumphteam.horizon.component.Component
import dev.triumphteam.horizon.html.attributes.setAttribute
import kotlinx.browser.document
import org.w3c.dom.Element
import org.w3c.dom.Node
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@TagMarker
public interface FlowChild {
    public val parentComponent: Component
    public val element: Element
}

public interface Tag : FlowChild {
    public val tagName: String

    public fun update(new: Tag)
}

public abstract class AbstractTag(
    override val tagName: String,
    override val parentComponent: Component,
    initialAttributes: Map<String, String> = emptyMap(),
) : TagAttributeScope() {
    override val element: Element = document.createElement(tagName)

    init {
        initialAttributes.forEach { (key, value) -> setAttribute(key, value) }
    }

    override fun update(new: Tag) {
        updateElement(oldElement = element, newElement = new.element)
    }

    private fun updateElement(oldElement: Element, newElement: Element) {
        // We need the attribute names to check if some were removed or added.
        val existingAttributeNames = oldElement.getAttributeNames()
        val newAttributeNames = newElement.getAttributeNames()

        // Now we need to remove attributes that no longer exist.
        for (name in existingAttributeNames) {
            if (name in newAttributeNames) continue
            oldElement.removeAttribute(name)
        }

        // Now we can update the attributes.
        for (name in newAttributeNames) {
            val newValue = newElement.getAttribute(name) ?: continue
            // They are the same, so no need to update.
            if (newValue == oldElement.getAttribute(name)) continue
            // Update to the new value.
            oldElement.setAttribute(name, newValue)
        }

        // Snapshot child lists to avoid issues with live NodeList mutation.
        val oldChildren = (0..<oldElement.childNodes.length).mapNotNull { oldElement.childNodes.item(it) }
        val newChildren = (0..<newElement.childNodes.length).mapNotNull { newElement.childNodes.item(it) }
        val newCount = newChildren.size
        val minCount = minOf(oldChildren.size, newChildren.size)

        for (index in 0..<minCount) {
            val existingChild = oldChildren[index]
            val newChild = newChildren[index]

            // If they are the same type.
            if (existingChild.nodeName == newChild.nodeName) {
                // We also check if they are elements, as they can be updated differently.
                if (existingChild is Element && newChild is Element) {
                    updateElement(oldElement = existingChild, newElement = newChild)
                    continue
                }

                // For text nodes, just compare and update content.
                if (existingChild.nodeType == Node.TEXT_NODE && existingChild.textContent != newChild.textContent) {
                    existingChild.textContent = newChild.textContent
                    continue
                }

                if (existingChild.isEqualNode(newChild)) continue
            }

            // Replace directly — move the node instead of cloning to preserve event listeners.
            oldElement.replaceChild(newChild, existingChild)
        }

        // Now we can remove extra existing children (iterate in reverse to avoid index shifting).
        for (index in (oldChildren.size - 1) downTo newCount) {
            oldElement.removeChild(oldChildren[index])
        }

        // And finally add the new children (move, not clone).
        for (index in minCount..<newCount) {
            oldElement.appendChild(newChildren[index])
        }
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

    @UnsafeApi
    public inline fun innerHtml(html: String) {
        element.innerHTML = html
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
