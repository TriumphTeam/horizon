package dev.triumphteam.horizon.component.functional

import dev.triumphteam.horizon.dom.HorizonRenderer
import dev.triumphteam.horizon.html.HtmlConsumer
import dev.triumphteam.horizon.html.HtmlScope
import dev.triumphteam.horizon.html.tag.HtmlMarker
import dev.triumphteam.horizon.html.tag.visit

internal typealias ElementComponentRender = HtmlScope.() -> Unit

public interface FunctionalElementComponent : StateHolder {
    public fun render(block: ElementComponentRender)
}

@HtmlMarker
public inline fun HtmlConsumer.elementComponent(block: FunctionalElementComponent.() -> Unit) {

}
