package dev.triumphteam.horizon.component

import dev.triumphteam.horizon.dom.createDomElements
import dev.triumphteam.horizon.html.CustomHtmlVisitorTag
import dev.triumphteam.horizon.html.HtmlRenderer
import dev.triumphteam.horizon.html.HtmlVisitor
import dev.triumphteam.horizon.html.tag.HtmlMarker
import dev.triumphteam.horizon.html.tag.visit
import dev.triumphteam.horizon.state.AbstractMutableState
import dev.triumphteam.horizon.state.MutableState
import dev.triumphteam.horizon.state.SimpleMutableState
import dev.triumphteam.horizon.state.State
import org.w3c.dom.Element
import org.w3c.dom.Node

internal typealias ComponentRender = HtmlVisitor.() -> Unit

@PublishedApi
internal interface Component {

    fun unmount()

    fun render()

    fun cleanUpDom()

    fun renderToDom()

    fun addRenderedChild(component: Component)
}

@PublishedApi
internal object EmptyComponent : Component {
    override fun unmount() {}
    override fun render() {}
    override fun cleanUpDom() {}
    override fun renderToDom() {}
    override fun addRenderedChild(component: Component) {}
}

@PublishedApi
internal class ReactiveComponent(
    private val parent: Component,
    private val boundNode: Node,
    private val render: ComponentRender,
    private val states: List<State<*>>,
) : Component {

    private var renderedElements: List<Element>? = null
    private val rendered: MutableList<Component> = mutableListOf()

    private val lastElementAtCreation = boundNode.lastChild

    override fun unmount() {
        // Unmount child components.
        rendered.forEach(Component::unmount)
        // Then the list of previously rendered components.
        rendered.clear()
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
        renderedElements?.forEach(boundNode::removeChild)
        renderedElements = null
    }

    override fun render() {
        renderToDom()
        // Tell the parent that this component has been rendered.
        parent.addRenderedChild(this)
    }

    override fun renderToDom() {
        // Create elements for this component.
        val elements = renderedElements ?: createDomElements(this, render)
            .also { renderedElements = it } // Cache it after rendering.

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

    override fun addRenderedChild(component: Component) {
        rendered += component
    }
}

public interface FunctionalComponent {

    public fun <T> remember(initialValue: T): MutableState<T>

    public fun <T> remember(state: State<T>): State<T>

    @HtmlMarker
    public fun render(block: ComponentRender)
}

@PublishedApi
internal class SimpleFunctionalComponent : FunctionalComponent {

    private var render: ComponentRender? = null
    private val states = mutableListOf<State<*>>()

    override fun <T> remember(initialValue: T): MutableState<T> {
        return SimpleMutableState(initialValue).also(states::add)
    }

    override fun <T> remember(state: State<T>): State<T> = state.also(states::add)

    override fun render(block: ComponentRender) {
        this.render = block
    }

    @PublishedApi
    internal fun getStates(): List<State<*>> = states.toList()

    @PublishedApi
    internal fun getComponentRender(): ComponentRender = render ?: {}
}

@PublishedApi
internal class ComponentTag(
    override val renderer: HtmlRenderer,
    internal val functionalComponent: SimpleFunctionalComponent,
    override val attributes: MutableMap<String, String> = mutableMapOf(),
) : CustomHtmlVisitorTag() {
    override val tagName: String = "component"
    override val isVoid: Boolean = true
}

@HtmlMarker
public inline fun HtmlVisitor.component(block: FunctionalComponent.() -> Unit) {
    ComponentTag(renderer, SimpleFunctionalComponent().apply(block))
        .visit(renderer) {}
}
