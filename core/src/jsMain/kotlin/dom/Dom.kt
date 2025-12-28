package dev.triumphteam.horizon.dom

import dev.triumphteam.horizon.component.Component
import dev.triumphteam.horizon.component.ComponentTag
import dev.triumphteam.horizon.component.ReactiveComponent
import dev.triumphteam.horizon.html.CustomHtmlTag
import dev.triumphteam.horizon.html.HtmlConsumer
import dev.triumphteam.horizon.html.HtmlRenderer
import dev.triumphteam.horizon.html.OldAbstractDomHtmlRenderer
import dev.triumphteam.horizon.state.AbstractMutableState
import org.w3c.dom.Element
import org.w3c.dom.Node

internal inline fun createDomElements(parent: Component, parentElement: Node, block: HtmlConsumer.() -> Unit) =
    DomRenderer(parent, parentElement).apply(block).render()

internal class DomRenderer(private val parent: Component, private val parentElement: Node) :
    OldAbstractDomHtmlRenderer() {

    override fun onCustomTagStart(tag: CustomHtmlTag, last: Element?) {
        if (tag !is ComponentTag) error("Tried to render unknown custom tag '${tag.tagName}'.")

        val states = tag.functionalComponent.getStates()

        // Create the component.
        val component = ReactiveComponent(
            boundNode = last ?: parentElement,
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

        // If we do have an element, add it as a child.
        if (last != null) {
            elements.forEach(last::appendChild)
            return
        }

        // Else add it to the path.
        elements.forEach(path::add)
    }

    override fun onCustomTagEnd(tag: CustomHtmlTag) {
        if (tag is ComponentTag) return
    }

    override fun createHtmlRenderer(): HtmlRenderer {
        return DomRenderer(parent, parentElement)
    }
}

// NOTES TO SELF
// Tags hold their own content
// Tags can check placement of content from own renderer
// Or content can be encoded as a special void tag with a value
// This would avoid the placement bs from above
