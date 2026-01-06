package dev.triumphteam.horizon.component.functional

import dev.triumphteam.horizon.component.ComponentRender

public interface FunctionalComponent : StateHolder {

    // @HtmlMarker
    public fun render(block: ComponentRender)
}

@PublishedApi
internal class SimpleFunctionalComponent : AbstractStateHolder(), FunctionalComponent {

    private var render: ComponentRender? = null

    override fun render(block: ComponentRender) {
        this.render = block
    }

    @PublishedApi
    internal fun getComponentRender(): ComponentRender = render ?: {}
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
