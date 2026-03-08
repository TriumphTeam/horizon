package dev.triumphteam.horizon.component

import dev.triumphteam.horizon.html.FlowContent
import dev.triumphteam.horizon.html.Tag
import dev.triumphteam.horizon.html.createHtml
import dev.triumphteam.horizon.state.AbstractState
import dev.triumphteam.horizon.state.State
import kotlinx.browser.document
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import org.w3c.dom.Comment
import org.w3c.dom.Element
import org.w3c.dom.Node

internal typealias ComponentRenderFunction = FlowContent.() -> Unit

public interface Component : ReactiveElement {

    public val renderedElements: List<Tag>

    public fun create()

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
    private val onCreate: (() -> Unit)?,
    private val onDestroy: (() -> Unit)?,
) : Component {

    protected val children: MutableList<Component> = mutableListOf()

    override fun create() {
        onCreate?.invoke()
        render()
    }

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

        // Makes sure to call the call back before the component is destroyed.
        onDestroy?.invoke()

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
    onCreate: (() -> Unit)?,
    onDestroy: (() -> Unit)?,
) : AbstractComponent(states, scope, onCreate, onDestroy) {

    private val startMarker: Comment = document.createComment("component-start")
    private val endMarker: Comment = document.createComment("component-end")

    override val renderedElements: MutableList<Tag> = mutableListOf()

    init {
        insertMarkers(parentComponent)
    }

    override fun render() {
        // Copy the previous elements to make it easier to modify the current ones.
        val previousElements = renderedElements.toList()
        // Then clear the existing elements.
        renderedElements.clear()

        // We need the iterator to compare the previous elements with the new ones.
        val previousIterator = previousElements.iterator()

        fun appendElement(element: Tag) {
            // Mark the element as rendered.
            renderedElements += element

            // The only option left is to append before.
            boundNode.insertBefore(element.element, endMarker)
        }

        fun createElements() {
            // Then proceed to attempt to create the HTML for the component.
            createHtml(parentComponent = this, element = boundNode, renderFunction = renderFunction) { newElement ->
                // Check if we need to compare elements.
                when {
                    previousIterator.hasNext() -> {
                        val previousElement = previousIterator.next()

                        // If they are the same type.
                        if (newElement.tagName == previousElement.tagName) {
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
                    else -> appendElement(newElement)
                }
            }

            // Remove all previous elements that were not used.
            while (previousIterator.hasNext()) {
                previousIterator.next().element.remove()
            }
        }

        // If we do have it, we insert before it.
        createElements()
    }

    override fun fullClear() {
        super.fullClear()

        // Then also clear the rendered elements.
        renderedElements.forEach { tag ->
            tag.element.remove()
        }
        renderedElements.clear()

        // Also remove the markers.
        startMarker.remove()
        endMarker.remove()
    }

    private fun insertMarkers(parentComponent: Component) {
        val anchor = parentComponent.renderedElements.lastOrNull()?.element?.nextSibling

        if (anchor != null && anchor.parentNode == boundNode) {
            boundNode.insertBefore(startMarker, anchor)
            boundNode.insertBefore(endMarker, anchor)
            return
        }

        boundNode.appendChild(startMarker)
        boundNode.appendChild(endMarker)
    }
}

