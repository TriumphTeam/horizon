package dev.triumphteam.horizon.component

import dev.triumphteam.horizon.dom.DOMBuilder
import kotlinx.browser.document
import kotlinx.html.HtmlTagMarker
import kotlinx.html.Tag
import kotlinx.html.TagConsumer
import kotlinx.html.consumers.onFinalize
import kotlinx.html.dom.createTree
import kotlinx.html.visitAndFinalize
import org.w3c.dom.HTMLElement
import org.w3c.dom.Node

internal typealias ComponentRender = TagConsumer<HTMLElement>.() -> Unit

internal interface Component {

    fun unmount()

    fun render()

    fun addRenderedChild(component: Component)
}

@PublishedApi
internal object EmptyComponent : Component {
    override fun unmount() {}
    override fun render() {}
    override fun addRenderedChild(component: Component) {}
}

@PublishedApi
internal class CachedComponent(
    private val parent: Component,
    private val boundNode: Node,
    private val render: ComponentRender,
) : Component {

    private var cachedElements: List<HTMLElement>? = null
    private val rendered: MutableList<Component> = mutableListOf()

    override fun unmount() {
        // Unmount child components.
        rendered.forEach(Component::unmount)
        // Then the list of previously rendered components.
        rendered.clear()
    }

    override fun render() {
        // Create elements for this component.
        val elements = cachedElements ?: buildList {
            DOMBuilder<HTMLElement>(this@CachedComponent).onFinalize { element, partial ->
                if (!partial) add(element)
            }.render()
        }

        // Actually render to dom.
        elements.forEach(boundNode::appendChild)

        // Tell the parent that this component has been rendered.
        parent.addRenderedChild(this)
    }

    override fun addRenderedChild(component: Component) {
        rendered += component
    }
}

public interface FunctionalComponent {

    // public fun <T> remember(initialValue: T): MutableState<T>

    public fun render(block: TagConsumer<HTMLElement>.() -> Unit)
}

@PublishedApi
internal class SimpleFunctionalComponent : FunctionalComponent {

    private var render: ComponentRender? = null
    // private val states = mutableListOf<MutableState<*>>()

    /*override fun <T> remember(initialValue: T): MutableState<T> {
        return SimpleState(initialValue).also(states::add)
    }*/

    override fun render(block: ComponentRender) {
        this.render = block
    }

    @PublishedApi
    internal fun getComponentRender(): ComponentRender = render ?: {}
}

@PublishedApi
internal class ComponentTag(
    override val consumer: TagConsumer<*>,
    internal val functionalComponent: SimpleFunctionalComponent,
) : Tag {
    override val attributes: MutableMap<String, String> = mutableMapOf()
    override val attributesEntries: Collection<Map.Entry<String, String>> = attributes.entries
    override val emptyTag: Boolean = true
    override val inlineTag: Boolean = true
    override val namespace: String? = null
    override val tagName: String = "component"
}

@HtmlTagMarker
public inline fun <T, C : TagConsumer<T>> C.component(block: FunctionalComponent.() -> Unit) {
    ComponentTag(this, SimpleFunctionalComponent().apply(block))
        .visitAndFinalize(this) {}
}
