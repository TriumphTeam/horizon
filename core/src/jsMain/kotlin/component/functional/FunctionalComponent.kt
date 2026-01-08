package dev.triumphteam.horizon.component.functional

import dev.triumphteam.horizon.component.ComponentRenderFunction
import dev.triumphteam.horizon.component.ReactiveComponent
import dev.triumphteam.horizon.html.FlowContent
import dev.triumphteam.horizon.html.TagMarker
import dev.triumphteam.horizon.state.AbstractMutableState

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

@TagMarker
public fun FlowContent.component(block: FunctionalComponent.() -> Unit) {
    val functionalComponent = SimpleFunctionalComponent().apply(block)
    val states = functionalComponent.getStates()

    println("making component on tag: ${element.nodeName}, with id: ${element.id}")
    // Create the component.
    val component = ReactiveComponent(
        boundNode = element,
        renderFunction = functionalComponent.getComponentRender(),
        states = states,
    )

    // Make sure the parent knows about this component.

    parentComponent.addChild(component)

    states.forEach { state ->
        if (state is AbstractMutableState) {
            state.addListener(component) {
                component.cleanUpDom()
                component.update()
            }
        }
    }

    // Then mount it.
    // TODO
    component.update()
}
