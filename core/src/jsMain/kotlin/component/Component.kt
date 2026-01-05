package dev.triumphteam.horizon.component

import dev.triumphteam.horizon.dom.createHorizonElements
import dev.triumphteam.horizon.dom.safeRemoveChild
import dev.triumphteam.horizon.html.HtmlConsumer
import dev.triumphteam.horizon.state.AbstractMutableState
import dev.triumphteam.horizon.state.State
import kotlinx.coroutines.CoroutineScope
import org.w3c.dom.Node
import kotlin.coroutines.CoroutineContext

internal typealias ComponentRender = HtmlConsumer.() -> Unit

public interface Component : CoroutineScope {

    /** Create and return the HTML elements. */
    public fun mount(): List<Node>

    public fun unmount()

    public fun cleanUpDom()

    public fun update()

    public fun addChild(component: Component)
}

@PublishedApi
internal class ReactiveComponent(
    private val boundNode: Node,
    private val lastElementAtCreation: Node?,
    private val render: ComponentRender,
    private val states: List<State<*>>,
) : Component {

    private var renderedElements: List<Node>? = null
    private val children: MutableList<Component> = mutableListOf()

    override val coroutineContext: CoroutineContext
        get() = TODO("Not yet implemented")

    override fun unmount() {
        // Unmount child components.
        children.forEach(Component::unmount)
        // Then the list of previously rendered components.
        children.clear()
        // Clean up the states.
        states.forEach { state ->
            if (state is AbstractMutableState) {
                state.removeListener(this)
            }
        }
        // Then remove itself from the dom.
        cleanUpDom()
    }

    override fun cleanUpDom() {
        renderedElements?.forEach(boundNode::safeRemoveChild)
        renderedElements = null
    }

    override fun mount(): List<Node> {
        // Create elements for this component.
        return renderedElements ?: createHorizonElements(this, boundNode, render)
            .also {
                renderedElements = it
            } // Cache it after rendering.
    }

    override fun update() {
        // Create elements for this component.
        val elements = mount()

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

        // If this is null, it means we are the first element.
        val lastElement = lastElementAtCreation
        if (lastElement == null) {
            // Check if a first child exists.
            val firstElement = boundNode.firstChild

            // If it doesn't, we append as if we're the only elements.
            if (firstElement == null) {
                elements.forEach(boundNode::appendChild)
                return
            }

            // If we have a first element, we insert before it.
            elements.forEach { element -> boundNode.insertBefore(element, firstElement) }
            return
        }

        // Here we can assume we have a last element.
        // So we get its next sibling.
        val elementAfter = lastElement.nextSibling

        // If no sibling exists, we append as if we're the last element.
        if (elementAfter == null) {
            elements.forEach(boundNode::appendChild)
            return
        }

        // If we do have it, we insert before it.
        elements.forEach { element -> boundNode.insertBefore(element, elementAfter) }
    }

    override fun addChild(component: Component) {
        children += component
    }
}

public class ReactiveElementComponent(

) {

}
