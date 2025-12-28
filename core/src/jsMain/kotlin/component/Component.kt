package dev.triumphteam.horizon.component

import dev.triumphteam.horizon.dom.createDomElements
import dev.triumphteam.horizon.html.CustomHtmlConsumerTag
import dev.triumphteam.horizon.html.HtmlRenderer
import dev.triumphteam.horizon.html.HtmlConsumer
import dev.triumphteam.horizon.html.tag.HtmlMarker
import dev.triumphteam.horizon.html.tag.visit
import dev.triumphteam.horizon.state.AbstractMutableState
import dev.triumphteam.horizon.state.MutableState
import dev.triumphteam.horizon.state.SimpleMutableState
import dev.triumphteam.horizon.state.State
import kotlinx.coroutines.CoroutineScope
import org.w3c.dom.Element
import org.w3c.dom.Node
import kotlin.coroutines.CoroutineContext

internal typealias ComponentRender = HtmlConsumer.() -> Unit

@PublishedApi
internal interface Component {

    /** Create and return the HTML elements. */
    fun mount(): List<Element>

    fun unmount()

    fun cleanUpDom()

    fun update()

    fun addChild(component: Component)
}

@PublishedApi
internal class ReactiveComponent(
    private val boundNode: Node,
    private val render: ComponentRender,
    private val states: List<State<*>>,
) : Component, CoroutineScope {

    private var renderedElements: List<Element>? = null
    private val rendered: MutableList<Component> = mutableListOf()

    private val lastElementAtCreation = boundNode.lastChild

    override val coroutineContext: CoroutineContext
        get() = TODO("Not yet implemented")

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

    override fun mount(): List<Element> {
        // Create elements for this component.
        return renderedElements ?: createDomElements(this, boundNode, render)
            .also {
                println("Elements created: ${it.map { it.tagName }}")
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
) : CustomHtmlConsumerTag() {
    override val tagName: String = "component"
    override val isVoid: Boolean = true
}

@HtmlMarker
public inline fun HtmlConsumer.component(block: FunctionalComponent.() -> Unit) {
    ComponentTag(renderer, SimpleFunctionalComponent().apply(block))
        .visit(renderer) {}
}
