package dev.triumphteam.horizon.dom

import dev.triumphteam.horizon.component.Component
import dev.triumphteam.horizon.component.ComponentTag
import dev.triumphteam.horizon.component.ReactiveComponent
import dev.triumphteam.horizon.html.AbstractDomHtmlRenderer
import dev.triumphteam.horizon.html.CustomHtmlTag
import dev.triumphteam.horizon.html.HtmlConsumer
import dev.triumphteam.horizon.html.HtmlRenderer
import dev.triumphteam.horizon.state.AbstractMutableState
import org.w3c.dom.Node

internal inline fun createHorizonElements(parent: Component, parentElement: Node, block: HtmlConsumer.() -> Unit) =
    HorizonRenderer(parent, parentElement).apply(block).render()

public class HorizonRenderer(private val parent: Component, private val parentElement: Node) :
    AbstractDomHtmlRenderer() {

    override fun onCustomTagStart(tag: CustomHtmlTag) {
        if (tag !is ComponentTag) error("Tried to render unknown custom tag '${tag.tagName}'.")

        val states = tag.functionalComponent.getStates()

        // Create the component.
        val component = ReactiveComponent(
            boundNode = tag.boundNode ?: parentElement,
            lastElementAtCreation = elements.lastOrNull(),
            render = tag.functionalComponent.getComponentRender(),
            states = states,
        )

        // Make sure the parent knows about this component.
        parent.addChild(component)

        states.forEach { state ->
            if (state is AbstractMutableState) {
                state.addListener(component) {
                    component.cleanUpDom()
                    component.update()
                }
            }
        }

        // Then mount it.
        val elements = component.mount()
        // Add elements to the current list.
        elements.forEach(this.elements::add)
    }

    override fun onCustomTagEnd(tag: CustomHtmlTag) {
        if (tag is ComponentTag) return
    }

    override fun createHtmlRenderer(): HtmlRenderer {
        return HorizonRenderer(parent, parentElement)
    }
}

public fun Node.safeRemoveChild(child: Node) {
    if (child.parentNode == this) {
        removeChild(child)
    }
}
