package dev.triumphteam.horizon.html

import org.w3c.dom.events.Event

public actual abstract class HtmlScope : HtmlAttributeScope() {

    public inline var HtmlTag.onClick: (Event) -> Unit
        get() = error("Tried to `get()` OnClick function, which is not allowed.")
        set(noinline newValue) {
            renderer.onTagEvent(this, "onclick", newValue)
        }
}
