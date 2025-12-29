package dev.triumphteam.horizon.router

import dev.triumphteam.horizon.component.Component
import dev.triumphteam.horizon.component.ReactiveComponent
import dev.triumphteam.horizon.html.HtmlConsumer
import dev.triumphteam.horizon.html.tag.div
import dev.triumphteam.horizon.state.SimpleMutableState
import dev.triumphteam.horizon.state.State
import dev.triumphteam.horizon.state.policy.StructureEqualityPolicy
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.Element

internal typealias RouteBlock = HtmlConsumer.(Route) -> Unit

@PublishedApi
internal class Router(private val rootElement: Element) {

    companion object {
        const val NOT_FOUND_ROUTE = "/404"
        const val DEFAULT_INDEX_ROUTE = "/"
    }

    private var indexRoute: SegmentedRoute? = null

    private val routes: MutableList<SegmentedRoute> = mutableListOf(
        createSegmentedRoute("/404") {
            div { text("404 Not Found") }
        },
    )

    private var currentRoute: DecodedRoute? = null

    @PublishedApi
    internal fun index(block: RouteBlock) {
        indexRoute = createSegmentedRoute(DEFAULT_INDEX_ROUTE, block)
    }

    @PublishedApi
    internal fun route(path: String, block: RouteBlock) {
        this.routes += createSegmentedRoute(path, block)
    }

    internal fun navigateTo(path: String, pushState: Boolean = true) {
        // Trim out leading and trailing characters.
        val trimmedPath = path.trim().removePrefix("/").removePrefix("/")
        val pathSegments = if (trimmedPath.isEmpty()) emptyList() else trimmedPath.split("/")


        val route = when {
            pathSegments.isEmpty() -> indexRoute?.let { ParsedRoute(it, path, emptyMap()) }
            else -> findRoute(pathSegments, path)
        }

        if (route == null) {
            navigateTo(NOT_FOUND_ROUTE, pushState)
            return
        }

        handleRoute(route, pushState)
    }

    private fun findRoute(pathSegments: List<String>, path: String): ParsedRoute? {
        routes.forEach { route ->
            return tryParseRoute(route, path, pathSegments) ?: return@forEach
        }

        return null
    }

    private fun handleRoute(parsedRoute: ParsedRoute, pushState: Boolean) {
        // Found a matching route
        val currentRoute = this.currentRoute

        // If the route matches, we just need to update the variables.
        if (currentRoute != null && currentRoute.segmentedRoute == parsedRoute.route) {
            if (currentRoute.route.updateVariables(parsedRoute.variables) && pushState) {
                // Only push state if the variables changed and we are meant to push state.
                window.history.pushState(null, document.title, parsedRoute.path)
            }
            return
        }

        if (pushState) {
            window.history.pushState(null, document.title, parsedRoute.path)
        }

        val variablesRoute = SimpleRoute(parsedRoute.variables)

        val decodedRoute = DecodedRoute(
            segmentedRoute = parsedRoute.route,
            component = ReactiveComponent(
                boundNode = rootElement,
                lastElementAtCreation = null,
                render = {
                    parsedRoute.route.block.invoke(this, variablesRoute)
                },
                states = emptyList(),
            ),
            route = variablesRoute,
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
        path: String,
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
            path = path,
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
    val path: String,
    val variables: Map<String, String>,
)

internal class DecodedRoute(
    internal val segmentedRoute: SegmentedRoute,
    internal val component: Component,
    internal val route: SimpleRoute,
) {

    internal fun unmount() {
        component.unmount()
    }

    internal fun render() {
        component.update()
    }
}

internal class SimpleRoute(variables: Map<String, String>) : Route {

    private val variableStates = variables.mapValues { (name, value) ->
        SimpleMutableState(value, StructureEqualityPolicy())
    }

    override fun get(name: String): State<String> {
        return requireNotNull(variableStates[name]) {
            "Could not find route variable with name: $name"
        }
    }

    override fun getVariable(name: String): String {
        return requireNotNull(variableStates[name]?.value) {
            "Could not find route variable with name: $name"
        }
    }

    override fun getVariableNullable(name: String): String? {
        return variableStates[name]?.value
    }

    internal fun updateVariables(variables: Map<String, String>): Boolean {
        return variables.map { (name, newValue) ->
            variableStates[name]?.setValue(newValue) ?: return@map false
        }.any { it }
    }
}
