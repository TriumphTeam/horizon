package dev.triumphteam.horizon.component

import dev.triumphteam.horizon.html.FlowContent
import dev.triumphteam.horizon.html.Tag
import dev.triumphteam.horizon.html.createHtml
import dev.triumphteam.horizon.state.AbstractMutableState
import dev.triumphteam.horizon.state.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.w3c.dom.Element
import org.w3c.dom.Node
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

internal typealias ComponentRenderFunction = FlowContent.() -> Unit

public interface Component : ReactiveElement, CoroutineScope {

    public fun render()

    public fun refresh()

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
        clear()
    }

    override fun addChild(component: Component) {
        children += component
    }

    protected open fun clear() {
        // Destroy all children first.
        children.forEach(Component::destroy)
        children.clear()
    }
}

@PublishedApi
internal class ReactiveComponent(
    private val boundNode: Element,
    private val parentComponent: Component,
    private val renderFunction: ComponentRenderFunction,
    states: List<State<*>>,
) : AbstractComponent(states) {

    private val lastElementAtCreation: Node? = boundNode.lastChild
    private val renderedElements: MutableList<Tag> = mutableListOf()

    override fun render() {
        // Scenario A
        // - this
        // - div
        // - div

        // Last element null
        // Get the first element and insert before or append.

        // Scenario B
        // - div
        // - this
        // - div

        // Last element div
        // Find the element after and prepend or append.

        fun createAndAppendElements(target: Node? = null) {
            createHtml(parentComponent = this, element = boundNode, renderFunction = renderFunction) { tag ->
                when {
                    target == null -> boundNode.appendChild(tag.element)
                    else -> boundNode.insertBefore(tag.element, target)
                }

                renderedElements.add(tag)
            }
        }

        // If this is null, it means we are the first element.
        val lastElement = lastElementAtCreation

        if (lastElement == null) {
            // Check if a first child exists.
            val firstElement = boundNode.firstChild

            // If it doesn't, we append as if we're the only elements.
            if (firstElement == null) {
                createAndAppendElements()
                return
            }

            // If we have a first element, we insert before it.
            createAndAppendElements(firstElement)
            return
        }

        // Here we can assume we have a last element.
        // So we get its next sibling.
        val elementAfter = lastElement.nextSibling

        // If no sibling exists, we append as if we're the last element.
        if (elementAfter == null) {
            createAndAppendElements()
            return
        }

        // If we do have it, we insert before it.
        createAndAppendElements(elementAfter)
    }

    override fun clear() {
        super.clear()

        // Then also clear the rendered elements.
        renderedElements.forEach { tag -> boundNode.safeRemoveChild(tag.element) }
        renderedElements.clear()
    }
}

internal fun Node.safeRemoveChild(child: Node) {
    if (child.parentNode == this) {
        removeChild(child)
    }
}
