package dev.triumphteam.horizon.component

import dev.triumphteam.horizon.dom.safeRemoveChild
import dev.triumphteam.horizon.html.FlowContent
import dev.triumphteam.horizon.html.Tag
import dev.triumphteam.horizon.html.createHtml
import dev.triumphteam.horizon.state.AbstractMutableState
import dev.triumphteam.horizon.state.State
import kotlinx.coroutines.CoroutineScope
import org.w3c.dom.Node
import kotlin.coroutines.CoroutineContext

internal typealias ComponentRender = FlowContent.() -> Unit

public interface Component : CoroutineScope {

    public fun mount(): List<Tag>

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

    private var renderedElements: List<Tag>? = null
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
        renderedElements?.forEach { tag ->
            boundNode.safeRemoveChild(tag.element)
        }
        renderedElements = null
    }

    override fun mount(): List<Tag> {
        // Create elements for this component.
        return renderedElements ?: createHtml(render)
        //createHorizonElements(this, boundNode, render)
        /*.also {
            renderedElements = it
        } // Cache it after rendering.*/
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
                elements.forEach { boundNode.appendChild(it.element) }
                return
            }

            // If we have a first element, we insert before it.
            elements.forEach { tag -> boundNode.insertBefore(tag.element, firstElement) }
            return
        }

        // Here we can assume we have a last element.
        // So we get its next sibling.
        val elementAfter = lastElement.nextSibling

        // If no sibling exists, we append as if we're the last element.
        if (elementAfter == null) {
            elements.forEach { boundNode.appendChild(it.element) }
            return
        }

        // If we do have it, we insert before it.
        elements.forEach { tag -> boundNode.insertBefore(tag.element, elementAfter) }
    }

    override fun addChild(component: Component) {
        children += component
    }
}
