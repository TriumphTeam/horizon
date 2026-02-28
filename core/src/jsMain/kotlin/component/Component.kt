package dev.triumphteam.horizon.component

import dev.triumphteam.horizon.html.FlowContent
import dev.triumphteam.horizon.html.Tag
import dev.triumphteam.horizon.html.createHtml
import dev.triumphteam.horizon.state.AbstractState
import dev.triumphteam.horizon.state.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import org.w3c.dom.Element
import org.w3c.dom.Node

internal typealias ComponentRenderFunction = FlowContent.() -> Unit

public interface Component : ReactiveElement {

    public val renderedElements: List<Tag>

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
    private val scope: CoroutineScope,
) : Component {

    protected val children: MutableList<Component> = mutableListOf()

    override fun refresh() {
        clear()
        render()
    }

    override fun destroy() {
        // Clean up the states.
        states.forEach { state ->
            if (state is AbstractState) {
                state.removeListener(this)
            }
        }

        // When being destroyed, we have to cancel all coroutines running.
        scope.cancel()

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
    parentComponent: Component,
    private val renderFunction: ComponentRenderFunction,
    states: List<State<*>>,
    scope: CoroutineScope,
) : AbstractComponent(states, scope) {

    private val lastNodeAtCreation: Node? =
        parentComponent.renderedElements.lastOrNull()?.element ?: boundNode.lastChild
    override val renderedElements: MutableList<Tag> = mutableListOf()

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

        fun createElements(anchor: Node?) {
            // Then proceed to attempt to create the HTML for the component.
            createHtml(parentComponent = this, element = boundNode, renderFunction = renderFunction) { newElement ->
                // Check if we need to compare elements.
                when {
                    previousIterator.hasNext() -> {
                        val previousElement = previousIterator.next()

                        // If they are the same type.
                        if (newElement.tagName == previousElement.tagName) {
                            println("DEBUG: same tag1!! ${newElement.tagName}")
                            // Reuse the existing node by just updating it instead.
                            previousElement.update(newElement)
                            renderedElements += previousElement
                            return@createHtml
                        }

                        // Different element completely so we need to replace it.
                        boundNode.replaceChild(newElement.element, previousElement.element)
                        renderedElements += newElement
                    }

                    // If we have nothing to compare, we append it.
                    else -> appendElement(newElement, anchor)
                }
            }

            // Remove all previous elements that were not used.
            while (previousIterator.hasNext()) {
                previousIterator.next().element.remove()
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

