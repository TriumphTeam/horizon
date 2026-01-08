package dev.triumphteam.horizon.router

import dev.triumphteam.horizon.Application
import dev.triumphteam.horizon.html.FlowContent
import dev.triumphteam.horizon.html.Tag
import dev.triumphteam.horizon.html.TagMarker
import dev.triumphteam.horizon.html.a
import dev.triumphteam.horizon.html.element.ATag
import kotlinx.browser.window

@PublishedApi
internal fun resolveRelativePath(to: String): String {
    val currentPath = window.location.pathname.trimEnd('/')

    // base -> /first/second
    // nothing -> append -> to = path -> result = /first/second/path
    // / -> direct -> to = /path -> result = /path
    // ../ -> replace -> to = ../path -> result = /first/path

    return when {
        to.startsWith("/") -> to // Direct path.
        to.startsWith("../") -> { // Replace last segment.

            val corrected = to.removePrefix("../")
            val segments = currentPath.split("/").toMutableList()

            // Remove the last segment to replace it.
            segments.removeLast()

            // Then join the segments again.
            "${segments.joinToString("/")}/$corrected"
        }

        else -> "$currentPath/$to" // Append path.
    }

}

@TagMarker
public inline fun FlowContent.navigate(to: String, crossinline block: ATag.() -> Unit = {}): Tag {
    val resolvedPath = resolveRelativePath(to)
    return a {
        href = resolveRelativePath(to)
        onClick = { event ->
            event.preventDefault()
            Application.goTo(resolvedPath)
        }
        block()
    }
}
