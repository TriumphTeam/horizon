package dev.triumphteam.horizon.router

import dev.triumphteam.horizon.Application
import kotlinx.html.A
import kotlinx.html.HTMLTag
import kotlinx.html.HtmlTagMarker
import kotlinx.html.Tag
import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.visitAndFinalize
import kotlinx.browser.window

public interface Navigate : Tag

@PublishedApi
internal class NavigateTag(
    initialAttributes: Map<String, String>,
    override val consumer: TagConsumer<*>,
) : HTMLTag("a", consumer, initialAttributes, null, true, false), Navigate

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

@HtmlTagMarker
public inline fun <T, C : TagConsumer<T>> C.navigate(
    to: String,
    classes: String? = null,
    crossinline block: Navigate.() -> Unit = {},
): T {
    val resolvedPath = resolveRelativePath(to)
    return NavigateTag(attributesMapOf("href", resolvedPath, "class", classes), this)
        .visitAndFinalize(this) {
            consumer.onTagEvent(this, "onclick") { event ->
                event.preventDefault()
                Application.goTo(resolvedPath)
            }
            block()
        }
}
