package dev.triumphteam.horizon.component.functional

import dev.triumphteam.horizon.component.ComponentRenderFunction

public interface FunctionalComponent : StateHolder {

    // @HtmlMarker
    public fun render(block: ComponentRenderFunction)
}

@PublishedApi
internal class SimpleFunctionalComponent : AbstractStateHolder(), FunctionalComponent {

    private var render: ComponentRenderFunction? = null

    override fun render(block: ComponentRenderFunction) {
        this.render = block
    }

    @PublishedApi
    internal fun getComponentRender(): ComponentRenderFunction = render ?: {}
}

/*@PublishedApi
internal class ComponentTag(
    parentRenderer: HtmlRenderer,
    internal val boundNode: Element?,
    internal val functionalComponent: SimpleFunctionalComponent,
    override val attributes: MutableMap<String, String> = mutableMapOf(),
) : CustomHtmlConsumerTag(parentRenderer) {
    override val tagName: String = "component"
    override val isVoid: Boolean = false
}

@HtmlMarker
public inline fun HtmlConsumer.component(block: FunctionalComponent.() -> Unit) {
    ComponentTag(
        parentRenderer = renderer,
        boundNode = (parentRenderer as? HorizonRenderer)?.current,
        functionalComponent = SimpleFunctionalComponent().apply(block),
    ).visit {}
}*/
