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
    private val states: List<MutableState<*>>,
) : Component {

    private var renderedElements: List<Element>? = null
    private val rendered: MutableList<Component> = mutableListOf()

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

        // Actually render to dom.
        elements.forEach(boundNode::appendChild)
    }

    override fun addRenderedChild(component: Component) {
        rendered += component
    }
}

public interface FunctionalComponent {

    public fun <T> remember(initialValue: T): MutableState<T>

    @HtmlMarker
    public fun render(block: ComponentRender)
}

@PublishedApi
internal class SimpleFunctionalComponent : FunctionalComponent {

    private var render: ComponentRender? = null
    private val states = mutableListOf<MutableState<*>>()

    override fun <T> remember(initialValue: T): MutableState<T> {
        return SimpleMutableState(initialValue).also(states::add)
    }

    override fun render(block: ComponentRender) {
        this.render = block
    }

    @PublishedApi
    internal fun getStates(): List<MutableState<*>> = states.toList()

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
