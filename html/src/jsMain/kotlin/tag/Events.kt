package dev.triumphteam.horizon.html.tag

import org.w3c.dom.events.Event

public fun HtmlTag.onClick(function: (Event) -> Unit) {
    renderer.onTagEvent(this, "onclick", function)
}
