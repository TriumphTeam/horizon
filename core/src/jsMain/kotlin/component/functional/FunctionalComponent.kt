package dev.triumphteam.horizon.component.functional

import dev.triumphteam.horizon.component.ComponentRenderFunction
import dev.triumphteam.horizon.component.ReactiveComponent
import dev.triumphteam.horizon.html.FlowContent
import dev.triumphteam.horizon.html.TagMarker
import dev.triumphteam.horizon.state.AbstractState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

public interface FunctionalComponent : StateHolder, CoroutineScope {

    public fun onCreate(block: () -> Unit)

    public fun onDestroy(block: () -> Unit)

    @TagMarker
    public fun render(block: ComponentRenderFunction)
}

@PublishedApi
internal class SimpleFunctionalComponent : AbstractStateHolder(), FunctionalComponent {

    private var render: ComponentRenderFunction? = null
    private var onCreate: (() -> Unit)? = null
    private var onDestroy: (() -> Unit)? = null

    override val coroutineContext: CoroutineContext =
        SupervisorJob() + Dispatchers.Default

    override fun onCreate(block: () -> Unit) {
        onCreate = block
    }

    override fun onDestroy(block: () -> Unit) {
        onDestroy = block
    }

    override fun render(block: ComponentRenderFunction) {
        this.render = block
    }

    @PublishedApi
    internal fun getComponentRender(): ComponentRenderFunction = render ?: {}

    @PublishedApi
    internal fun getOnCreate(): (() -> Unit)? = onCreate

    @PublishedApi
    internal fun getOnDestroy(): (() -> Unit)? = onDestroy
}

@TagMarker
public fun FlowContent.component(block: FunctionalComponent.() -> Unit) {
    val functionalComponent = SimpleFunctionalComponent().apply(block)
    val states = functionalComponent.getStates()

    // Create the component.
    val component = ReactiveComponent(
        parentComponent = parentComponent,
        boundNode = element,
        renderFunction = functionalComponent.getComponentRender(),
        states = states,
        scope = functionalComponent,
        onCreate = functionalComponent.getOnCreate(),
        onDestroy = functionalComponent.getOnDestroy(),
    )

    // Make sure the parent knows about this component.
    parentComponent.addChild(component)

    // Make sure the states refresh the component correctly.
    states.forEach { state ->
        if (state is AbstractState) {
            state.addListener(component) {
                component.refresh()
            }
        }
    }

    // Initial render of the component.
    component.create()
}
