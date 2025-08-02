package dev.triumphteam.horizon

import kotlinx.browser.document
import kotlinx.html.HtmlTagMarker
import kotlinx.html.Tag
import kotlinx.html.TagConsumer
import kotlinx.html.consumers.onFinalize
import kotlinx.html.dom.createTree
import kotlinx.html.visitAndFinalize
import org.w3c.dom.HTMLElement
import org.w3c.dom.Node

internal typealias RenderedComponent = List<HTMLElement>

public class Component(
    private val parent: Node,
    private val render: ComponentRender,
) {

    private var cache: RenderedComponent? = null

    /**  On cleanup, we remove old nodes from [parent]. */
    public fun cleanup() {
        cache?.forEach(parent::removeChild)
    }

    /** Renders and caches component. */
    public fun renderToParent() {

        val elements = cache ?: buildList {
            document.createTree().onFinalize { element, partial ->
                if (!partial) add(element)
            }.render()
        }.also { cache = it }

        // Render to parent node.
        elements.forEach(parent::appendChild)
    }

    /** Invalidates the component so it can re-render. */
    public fun invalidate() {
        cache = null
    }
}

public typealias ComponentRender = HtmlBuilder.() -> Unit

public interface FunctionalComponent {

    public fun <T> remember(initialValue: T): MutableState<T>

    public fun render(block: ComponentRender)
}

public class SimpleFunctionalComponent : FunctionalComponent {

    private var render: ComponentRender? = null
    private val states = mutableListOf<MutableState<*>>()

    override fun <T> remember(initialValue: T): MutableState<T> {
        return SimpleState(initialValue).also(states::add)
    }

    override fun render(block: ComponentRender) {
        this.render = block
    }

    public fun states(): List<MutableState<*>> = states.toList()
    public fun asComponent(): Component = Component(null!!, render ?: {})
}

@HtmlTagMarker
public inline fun <T, C : TagConsumer<T>> C.component(block: FunctionalComponent.() -> Unit) {

    println("uh")
    /*val functionalComponent = SimpleFunctionalComponent().apply(block)
    val component = functionalComponent.asComponent()*/

    onFinalize { element, partial ->
        println("finalizing component")
        println(element)
    }

    /*fun renderToDom() {
        // Clear
        root.clear()
        component.render().forEach(root::appendChild)
    }

    functionalComponent.states().forEach { state ->
        state.addListener {
            component.invalidate()
            renderToDom()
        }
    }

    renderToDom()*/
}

@HtmlTagMarker
public fun <T, C : TagConsumer<T>> C.test(): T {
    return Test(this).visitAndFinalize(this) {}
}

public class Test(override val consumer: TagConsumer<*>) : Tag {
    override val attributes: MutableMap<String, String> = mutableMapOf()

    override val attributesEntries: Collection<Map.Entry<String, String>> = emptyList()

    override val emptyTag: Boolean = true
    override val inlineTag: Boolean = true
    override val namespace: String? = null
    override val tagName: String = "test"
}
