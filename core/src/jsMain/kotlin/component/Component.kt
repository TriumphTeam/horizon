package dev.triumphteam.horizon.component

import dev.triumphteam.horizon.html.FlowContent
import dev.triumphteam.horizon.html.Tag
import dev.triumphteam.horizon.html.createHtml
import dev.triumphteam.horizon.state.AbstractMutableState
import dev.triumphteam.horizon.state.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.w3c.dom.Element
import org.w3c.dom.Node
import kotlin.coroutines.CoroutineContext

internal typealias ComponentRenderFunction = FlowContent.() -> Unit

public interface Component : ReactiveElement, CoroutineScope {

    /**
     * The render function is responsible for adding elements directly to the DOM.
     * The rendering is done at initialization and whenever the component's state changes.
     */
    public fun render()

    /** Refresh is a combination of clearing the component and its children and then rendering it again. */
    public fun refresh()

    /** Destruction is the final step of the component, clearing everything related to it and removing state listeners. */
    public fun destroy()

    public fun addChild(component: Component)
}

internal abstract class AbstractComponent(
    protected val states: List<State<*>>,
) : Component {

    protected val children: MutableList<Component> = mutableListOf()

    override val coroutineContext: CoroutineContext =
        SupervisorJob() + Dispatchers.Default

    override fun refresh() {
        clear()
        render()
    }

    override fun destroy() {
        // Clean up the states.
        states.forEach { state ->
            if (state is AbstractMutableState) {
                state.removeListener(this)
            }
        }

        // When being destroyed, we have to cancel all coroutines running.
        cancel()

        // Then clear the component.
        fullClear()
    }

    override fun addChild(component: Component) {
        children += component
    }

    protected open fun clear() {
        // Destroy all children first.
        children.forEach(Component::destroy)
        children.clear()
    }

    protected open fun fullClear() {
        clear()
    }
}

@PublishedApi
internal class ReactiveComponent(
    private val boundNode: Element,
    private val parentComponent: Component,
    private val renderFunction: ComponentRenderFunction,
    states: List<State<*>>,
) : AbstractComponent(states) {

    // TODO: FIX THIS
    private val lastNodeAtCreation: Node? = (parentComponent as? ReactiveComponent)?.renderedElements?.lastOrNull()?.element ?: boundNode.lastChild
    internal val renderedElements: MutableList<Tag> = mutableListOf()

    override fun render() {
        // Copy the previous elements to make it easier to modify the current ones.
        val previousElements = renderedElements.toList()
        // Then clear the existing elements.
        renderedElements.clear()

        // We need the iterator to compare the previous elements with the new ones.
        val previousIterator = previousElements.iterator()

        fun appendElement(element: Tag, anchor: Node?) {
            // Mark the element as rendered.
            renderedElements += element

            // Check if we need to append last or append before.
            if (anchor == null) {
                boundNode.appendChild(element.element)
                return
            }

            // The only option left is to append before.
            boundNode.insertBefore(element.element, anchor)
        }

        // TODO, NODE BEFORE IT STORED FOR NEXT DUDES

        fun updateElement(existing: Tag, new: Tag) {
            val existingElement = existing.element
            val newElement = new.element

            // Get all attribute names from both elements
            val existingAttrs = mutableSetOf<String>()
            val existingAttrsMap = mutableMapOf<String, String>()

            for (i in 0 until existingElement.attributes.length) {
                val attr = existingElement.attributes.item(i) ?: continue
                existingAttrs += attr.name
                existingAttrsMap[attr.name] = attr.value
            }

            val newAttrs = mutableSetOf<String>()
            val newAttrsMap = mutableMapOf<String, String>()

            for (i in 0 until newElement.attributes.length) {
                val attr = newElement.attributes.item(i) ?: continue
                newAttrs += attr.name
                newAttrsMap[attr.name] = attr.value
            }

            // Remove attributes that no longer exist
            for (attrName in existingAttrs) {
                if (attrName !in newAttrs) {
                    existingElement.removeAttribute(attrName)
                }
            }

            // Update or add new attributes
            for ((attrName, attrValue) in newAttrsMap) {
                if (existingAttrsMap[attrName] != attrValue) {
                    existingElement.setAttribute(attrName, attrValue)
                }
            }
        }

        fun updateElementAttributes(existingElement: Element, newElement: Element) {
            val existingAttrs = mutableSetOf<String>()
            val existingAttrsMap = mutableMapOf<String, String>()

            for (i in 0 until existingElement.attributes.length) {
                val attr = existingElement.attributes.item(i) ?: continue
                existingAttrs += attr.name
                existingAttrsMap[attr.name] = attr.value
            }

            val newAttrs = mutableSetOf<String>()
            val newAttrsMap = mutableMapOf<String, String>()

            for (i in 0 until newElement.attributes.length) {
                val attr = newElement.attributes.item(i) ?: continue
                newAttrs += attr.name
                newAttrsMap[attr.name] = attr.value
            }

            // Remove attributes that no longer exist
            for (attrName in existingAttrs) {
                if (attrName !in newAttrs) {
                    existingElement.removeAttribute(attrName)
                }
            }

            // Update or add new attributes
            for ((attrName, attrValue) in newAttrsMap) {
                if (existingAttrsMap[attrName] != attrValue) {
                    existingElement.setAttribute(attrName, attrValue)
                }
            }
        }


        fun updateChildren(existingElement: Element, newElement: Element) {
            val existingChildren = existingElement.childNodes
            val newChildren = newElement.childNodes

            val existingCount = existingChildren.length
            val newCount = newChildren.length
            val minCount = minOf(existingCount, newCount)

            // Update existing children
            for (i in 0 until minCount) {
                val existingChild = existingChildren.item(i) ?: continue
                val newChild = newChildren.item(i) ?: continue

                // Handle text nodes
                if (existingChild.nodeType == Node.TEXT_NODE && newChild.nodeType == Node.TEXT_NODE) {
                    if (existingChild.nodeValue != newChild.nodeValue) {
                        existingChild.nodeValue = newChild.nodeValue
                    }
                    continue
                }

                // Handle element nodes
                if (existingChild.nodeType == Node.ELEMENT_NODE && newChild.nodeType == Node.ELEMENT_NODE) {
                    val existingEl = existingChild as? Element ?: continue
                    val newEl = newChild as? Element ?: continue

                    // Same tag name - update it
                    if (existingEl.tagName.equals(newEl.tagName, ignoreCase = true)) {
                        updateElementAttributes(existingEl, newEl)
                        updateChildren(existingEl, newEl)
                    } else {
                        // Different tag - replace it
                        existingElement.replaceChild(newChild.cloneNode(true), existingChild)
                    }
                    continue
                }

                // Different node types - replace
                existingElement.replaceChild(newChild.cloneNode(true), existingChild)
            }

            // Remove extra existing children
            while (existingElement.childNodes.length > newCount) {
                existingElement.lastChild?.let { existingElement.removeChild(it) }
            }

            // Add new children
            for (i in minCount until newCount) {
                val newChild = newChildren.item(i) ?: continue
                existingElement.appendChild(newChild.cloneNode(true))
            }
        }

        fun createElements(anchor: Node?) {
            // Then proceed to attempt to create the HTML for the component.
            createHtml(parentComponent = this, element = boundNode, renderFunction = renderFunction) { tag ->

                // Check if we need to compare elements.
                if (previousIterator.hasNext()) {
                    val previousElement = previousIterator.next()

                    // TODO: Comparison.
                    // Compare if they are the same type of element
                    if (previousElement.tagName.equals(tag.tagName, ignoreCase = true)) {
                        // Same element type - update attributes
                        updateElement(previousElement, tag)

                        // Update children recursively
                        if (previousElement is FlowContent && tag is FlowContent) {
                            updateChildren(previousElement.element, tag.element)
                        }

                        renderedElements += previousElement
                    } else {
                        // Different element type - replace it
                        boundNode.replaceChild(tag.element, previousElement.element)
                        renderedElements += tag
                    }
                    return@createHtml
                }

                // If we have nothing to compare, we append it.
                appendElement(tag, anchor)
            }
        }

        val lastNode = lastNodeAtCreation

        // If this is null, it means we are the first node.
        if (lastNode == null) {
            // Check if an element already exists.
            val anchor = boundNode.firstChild

            // If nothing exists, it means this component is the only one.
            if (anchor == null) {
                createElements(null)
                return
            }

            // If we have a first element, we insert before it.
            createElements(anchor)
            return
        }

        // Here we can assume we have a node we must be after.
        // So we get its next sibling.
        val anchor = lastNode.nextSibling

        // If no sibling exists, we append as if we're the last node.
        if (anchor == null) {
            createElements(null)
            return
        }

        // If we do have it, we insert before it.
        createElements(anchor)
    }

    override fun fullClear() {
        super.fullClear()

        // Then also clear the rendered elements.
        renderedElements.forEach { tag ->
            tag.element.remove()
        }
        renderedElements.clear()
    }
}

