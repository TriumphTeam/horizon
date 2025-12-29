package dev.triumphteam.horizon.dom

import dev.triumphteam.horizon.component.Component
import dev.triumphteam.horizon.component.ComponentTag
import dev.triumphteam.horizon.component.ReactiveComponent
import dev.triumphteam.horizon.html.AbstractDomHtmlRenderer
import dev.triumphteam.horizon.html.CustomHtmlTag
import dev.triumphteam.horizon.html.HtmlConsumer
import dev.triumphteam.horizon.html.HtmlRenderer
import dev.triumphteam.horizon.html.HtmlTag
import dev.triumphteam.horizon.state.AbstractMutableState
import org.w3c.dom.Node

internal inline fun createDomElements(parent: Component, parentElement: Node, block: HtmlConsumer.() -> Unit) =
    DomRenderer(parent, parentElement).apply(block).render()

@PublishedApi
internal class DomRenderer(private val parent: Component, private val parentElement: Node) :
    AbstractDomHtmlRenderer() {

    override fun onStart(tag: HtmlTag) {
        println("Starting tag: ${tag.tagName}, id: ${tag.attributes["id"]}")
        super.onStart(tag)
    }

    override fun onCustomTagStart(tag: CustomHtmlTag) {
        if (tag !is ComponentTag) error("Tried to render unknown custom tag '${tag.tagName}'.")

        val states = tag.functionalComponent.getStates()

        println("Starting custom tag?")
        println(parentRenderer.hashCode())
        println("Current: ${tag.boundNode?.tagName}")

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
        return DomRenderer(parent, parentElement)
    }
}

// NOTES TO SELF
// Tags hold their own content
// Tags can check placement of content from own renderer
// Or content can be encoded as a special void tag with a value
// This would avoid the placement bs from above
