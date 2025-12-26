package dev.triumphteam.horizon.router

import dev.triumphteam.horizon.component.Component
import dev.triumphteam.horizon.component.EmptyComponent
import dev.triumphteam.horizon.component.ReactiveComponent
import dev.triumphteam.horizon.html.HtmlVisitor
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.Element

internal typealias RouteBlock = HtmlVisitor.() -> Unit

@PublishedApi
internal class Router(private val rootElement: Element) {

    private var indexRoute: SegmentedRoute? = null
    private var notFoundRoute: SegmentedRoute? = null

    private val routes: MutableList<SegmentedRoute> = mutableListOf()

    @PublishedApi
    internal var currentRoute: DecodedRoute? = null

    @PublishedApi
    internal fun route(path: String, block: RouteBlock) {
        this.routes += createSegmentedRoute(path, block)
    }

    internal fun navigateTo(path: String) {
        // Trim out leading and trailing characters.
        val trimmedPath = path.trim().removePrefix("/").removePrefix("/")
        val pathSegments = if (trimmedPath.isEmpty()) emptyList() else trimmedPath.split("/")

        // TODO, prolly needs to be changed.
        window.history.pushState(null, document.title, path)

        if (pathSegments.isEmpty()) {
            println("Using index route, $indexRoute")
            return
        }

        findAndHandleRoute(pathSegments)
    }

    private fun findAndHandleRoute(pathSegments: List<String>) {
        println("Going to find route for path: $pathSegments")

        fun findRoute(): ParsedRoute? {
            routes.forEach { route ->
                return tryParseRoute(route, pathSegments) ?: return@forEach
            }

            return null
        }

        val parsedRoute = findRoute()

        if (parsedRoute == null) {
            println("No route found, using not found route, $notFoundRoute")
            // window.history.pushState(null, document.title, "/404")
            return
        }

        // Found a matching route

        println("Going to check if routes are the same.")

        val currentRoute = this.currentRoute
        /*if (currentRoute != null && currentRoute.route == route) {
            // Found the same route, so we don't update anything.
            println("same route!!!")
            return
        }*/

        val decodedRoute = DecodedRoute(
            route = parsedRoute.route,
            component = ReactiveComponent(
                parent = EmptyComponent,
                boundNode = rootElement,
                render = {
                    parsedRoute.route.block.invoke(this)
                },
                states = emptyList(),
            ),
        )

        // Unmount current route so new one can take its place.
        currentRoute?.unmount()

        // We have a new route, so we need to set it as current.
        this.currentRoute = decodedRoute

        // Then render it to the dom.
        decodedRoute.render()
    }

    private fun tryParseRoute(
        route: SegmentedRoute,
        segments: List<String>,
    ): ParsedRoute? {

        val variables = mutableMapOf<String, String>()
        val segmentIterator = route.segments.iterator()

        // This might need to change in the future if we allow "infinite" segments.
        if (segments.size != route.segments.size) {
            return null
        }

        for (segment in segments) {
            // If there are more segments than the route allows, it won't match, so just exit.
            if (!segmentIterator.hasNext()) return null

            val routeSegment = segmentIterator.next()

            when (routeSegment.type) {
                // If the exact doesn't match, we also know it won't match at all.
                SegmentType.EXACT -> if (segment != routeSegment.name) return null
                else -> variables[routeSegment.name] = segment
            }
        }

        return ParsedRoute(
            route = route,
            variables = variables,
        )
    }

    private fun createSegmentedRoute(path: String, block: RouteBlock): SegmentedRoute {
        return SegmentedRoute(
            segments = path.trim('/')
                .split("/")
                .map { segment ->
                    when {
                        segment.startsWith(":") -> {
                            val segmentName = segment.removePrefix(":")
                            when {
                                segmentName.endsWith("?") -> Segment(
                                    name = segmentName.removeSuffix("?"),
                                    type = SegmentType.VARIABLE_OPTIONAL,
                                )

                                else -> Segment(name = segmentName, type = SegmentType.VARIABLE)
                            }
                        }

                        else -> Segment(name = segment, type = SegmentType.EXACT)
                    }
                },
            isIndex = false,
            block = block,
        )
    }
}

public enum class SegmentType {
    EXACT, VARIABLE, VARIABLE_OPTIONAL
}

public data class Segment(
    public val name: String,
    public val type: SegmentType,
)

@PublishedApi
internal data class SegmentedRoute(
    internal val segments: List<Segment>,
    internal val isIndex: Boolean = false,
    internal val block: RouteBlock,
)

private data class ParsedRoute(
    val route: SegmentedRoute,
    val variables: Map<String, String>,
)

internal data class DecodedRoute(
    internal val route: SegmentedRoute,
    internal val component: Component,
) {

    internal fun unmount() {
        component.unmount()
    }

    internal fun render() {
        component.render()
    }
}
