package dev.triumphteam.horizon.dom

import dev.triumphteam.horizon.component.Component
import dev.triumphteam.horizon.component.ComponentTag
import dev.triumphteam.horizon.component.ReactiveComponent
import dev.triumphteam.horizon.html.AbstractDomHtmlRenderer
import dev.triumphteam.horizon.html.CustomHtmlTag
import dev.triumphteam.horizon.html.HtmlVisitor
import dev.triumphteam.horizon.state.AbstractMutableState
import org.w3c.dom.Element

internal inline fun createDomElements(parent: Component, block: HtmlVisitor.() -> Unit) = DomRenderer(parent)
    .apply(block).render()

internal class DomRenderer(private val parent: Component) : AbstractDomHtmlRenderer() {

    override fun onCustomTagStart(tag: CustomHtmlTag, last: Element?) {
        if (tag !is ComponentTag) error("Tried to render unknown custom tag '${tag.tagName}'.")
        // TODO: Figure this shit out, fuck
        if (last == null) error("Tried to component without a parent.")

        val states = tag.functionalComponent.getStates()

        val component = ReactiveComponent(
            parent = parent,
            boundNode = last,
            render = tag.functionalComponent.getComponentRender(),
            states = states,
        )

        states.forEach { state ->
            if (state is AbstractMutableState) {
                state.addListener(component) {
                    component.cleanUpDom()
                    component.renderToDom()
                }
            }
        }

        component.render()
    }

    override fun onCustomTagEnd(tag: CustomHtmlTag) {
        if (tag is ComponentTag) return
    }
}
