package dev.triumphteam.horizon.component

import dev.triumphteam.horizon.html.FlowContent
import dev.triumphteam.horizon.html.Tag
import dev.triumphteam.horizon.html.createHtml
import dev.triumphteam.horizon.state.AbstractMutableState
import dev.triumphteam.horizon.state.State
import kotlinx.coroutines.CoroutineScope
import org.w3c.dom.Element
import org.w3c.dom.Node
import kotlin.coroutines.CoroutineContext

internal typealias ComponentRenderFunction = FlowContent.() -> Unit

public interface Component : CoroutineScope {

    public fun unmount()

    public fun cleanUpDom()

    public fun update()

    public fun addChild(component: Component)
}

@PublishedApi
internal class ReactiveComponent(
    private val boundNode: Element,
    private val renderFunction: ComponentRenderFunction,
    private val states: List<State<*>>,
) : Component {

    private val lastElementAtCreation: Node? = boundNode.lastChild

    private var renderedElements: MutableList<Tag> = mutableListOf()
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
        renderedElements.forEach { tag ->
            boundNode.safeRemoveChild(tag.element)
        }
        renderedElements.clear()
    }

    override fun update() {
        // Create elements for this component.


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
            createHtml(parentComponent = this, element = boundNode, block = renderFunction) { tag ->
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

    override fun addChild(component: Component) {
        children += component
    }
}

internal fun Node.safeRemoveChild(child: Node) {
    if (child.parentNode == this) {
        removeChild(child)
    }
}
