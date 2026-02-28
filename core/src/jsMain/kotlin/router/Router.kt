package dev.triumphteam.horizon.router

import dev.triumphteam.horizon.Application.rootElement
import dev.triumphteam.horizon.component.AbstractComponent
import dev.triumphteam.horizon.component.ComponentRenderFunction
import dev.triumphteam.horizon.html.FlowContent
import dev.triumphteam.horizon.html.Tag
import dev.triumphteam.horizon.html.createHtml
import dev.triumphteam.horizon.html.div
import dev.triumphteam.horizon.state.SimpleMutableState
import dev.triumphteam.horizon.state.State
import dev.triumphteam.horizon.state.policy.StructureEqualityPolicy
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.w3c.dom.Element

internal typealias RouteBlock<T> = FlowContent.(route: T) -> Unit
internal typealias RouteProvider<T> = (routeSegments: List<Segment>, variables: Map<String, String>) -> T?

@PublishedApi
internal class Router(private val rootElement: Element) {

    companion object {
        const val NOT_FOUND_ROUTE = "/404"
        const val DEFAULT_INDEX_ROUTE = "/"
    }

    private var indexRoute: SegmentedRoute<DefaultUnsegmentedRoute>? = null

    private val routes: MutableList<SegmentedRoute<*>> = mutableListOf(
        createSegmentedRoute(
            path = "/404",
            routeProvider = { _, _ -> DefaultUnsegmentedRoute },
        ) {
            div { text("404 Not Found") }
        },
    )

    private var currentRoute: RouteComponent<*>? = null

    @PublishedApi
    internal fun index(block: RouteBlock<DefaultUnsegmentedRoute>) {
        indexRoute = createSegmentedRoute(
            path = DEFAULT_INDEX_ROUTE,
            routeProvider = { _, _ -> DefaultUnsegmentedRoute },
            block = block,
        )
    }

    @PublishedApi
    internal fun route(path: String, block: RouteBlock<DefaultSegmentedRoute>) {
        this.routes += createSegmentedRoute(
            path = path,
            routeProvider = { routeSegments, variables ->
                DefaultSegmentedRoute(routeSegments, variables)
            },
            block = block,
        )
    }

    @PublishedApi
    internal fun <T : Route> route(
        path: String,
        routeProvider: RouteProvider<T>,
        block: RouteBlock<T>,
    ) {
        this.routes += createSegmentedRoute(path, routeProvider, block)
    }

    internal fun navigateTo(path: String, pushState: Boolean = true) {
        // Trim out leading and trailing characters.
        val trimmedPath = path.trim().removePrefix("/").removeSuffix("/")
        val pathSegments = if (trimmedPath.isEmpty()) emptyList() else trimmedPath.split("/")
        println("DEBUG: Going to $trimmedPath")
        println("DEBUG: Going to $pathSegments")

        val route = when {
            pathSegments.isEmpty() -> indexRoute?.let { ParsedRoute(it, path, emptyMap()) }
            else -> findRoute(pathSegments, path)
        }

        if (route == null) {
            notFound(pushState)
            return
        }

        handleRoute(route, pushState)
    }

    private fun findRoute(pathSegments: List<String>, path: String): ParsedRoute<*>? {
        routes.forEach { route ->
            return tryParseRoute(route, path, pathSegments) ?: return@forEach
        }

        return null
    }

    private fun handleRoute(parsedRoute: ParsedRoute<*>, pushState: Boolean) {
        // Found a matching route
        val currentRoute = this.currentRoute
        val route = parsedRoute.route

        // If the route matches, we just need to update the variables.
        if (currentRoute != null && currentRoute.segmentedRoute == route) {
            val result = currentRoute.route.updateVariables(parsedRoute.variables)

            when (result) {
                RouteVariablesUpdateResult.ERROR -> notFound(pushState)
                RouteVariablesUpdateResult.UPDATED -> {
                    if (!pushState) return
                    // Only push state if the variables changed and we are meant to push state.
                    window.history.pushState(null, document.title, parsedRoute.path)
                }

                else -> {}
            }
            return
        }

        if (pushState) {
            window.history.pushState(null, document.title, parsedRoute.path)
        }

        // If something happens when creating the component, we redirect to not found.
        val routeComponent = route.createComponent(parsedRoute.variables) ?: run {
            notFound(pushState)
            return
        }

        // Destroy the route so a new one can take its place.
        currentRoute?.destroy()

        // We have a new route, so we need to set it as current.
        this.currentRoute = routeComponent

        // Then render it to the dom.
        routeComponent.render()
    }

    private fun tryParseRoute(
        route: SegmentedRoute<*>,
        path: String,
        segments: List<String>,
    ): ParsedRoute<*>? {

        val variables = mutableMapOf<String, String>()
        val segmentIterator = route.segments.iterator()

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

    private fun <T : Route> createSegmentedRoute(
        path: String,
        routeProvider: RouteProvider<T>,
        block: RouteBlock<T>,
    ): SegmentedRoute<T> {
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
            routeProvider = routeProvider,
            block = block,
        )
    }

    private fun notFound(pushState: Boolean) {
        navigateTo(NOT_FOUND_ROUTE, pushState)
    }
}

public enum class SegmentType {
    EXACT, VARIABLE, VARIABLE_OPTIONAL;

    public fun isVariable(): Boolean {
        return this == VARIABLE || this == VARIABLE_OPTIONAL
    }
}

public data class Segment(
    public val name: String,
    public val type: SegmentType,
)

@PublishedApi
internal data class SegmentedRoute<T : Route>(
    internal val segments: List<Segment>,
    internal val isIndex: Boolean = false,
    private val routeProvider: RouteProvider<T>,
    private val block: RouteBlock<T>,
) {

    internal fun createComponent(variables: Map<String, String>): RouteComponent<T>? {
        val route = routeProvider(segments, variables) ?: return null

        return RouteComponent(
            segmentedRoute = this,
            rootElement = rootElement,
            renderFunction = {
                block(this, route)
            },
            route = route,
        )
    }
}

private data class ParsedRoute<T : Route>(
    val route: SegmentedRoute<T>,
    val path: String,
    val variables: Map<String, String>,
)

internal class RouteComponent<T : Route>(
    internal val segmentedRoute: SegmentedRoute<T>,
    internal val rootElement: Element,
    internal val renderFunction: ComponentRenderFunction,
    internal val route: T,
) : AbstractComponent(emptyList(), CoroutineScope(SupervisorJob() + Dispatchers.Default)) {

    override val renderedElements: MutableList<Tag> = mutableListOf()

    override fun render() {
        createHtml(parentComponent = this, element = rootElement, renderFunction = renderFunction) { tag ->
            rootElement.appendChild(tag.element)
            renderedElements += tag
        }
    }

    override fun fullClear() {
        super.fullClear()

        // Fully clear the root element.
        // Much easier than running through all elements to remove them.
        rootElement.innerHTML = ""
        renderedElements.clear()
    }
}

@PublishedApi
internal object DefaultUnsegmentedRoute : Route {
    override fun updateVariables(variables: Map<String, String>): RouteVariablesUpdateResult {
        return RouteVariablesUpdateResult.NOT_UPDATED
    }
}

@PublishedApi
internal class DefaultSegmentedRoute(routeSegments: List<Segment>, variables: Map<String, String>) : SimpleRoute {

    private val variableStates = routeSegments.filter { it.type.isVariable() }
        .associate { segment ->
            segment.name to SimpleMutableState(variables[segment.name] ?: "", StructureEqualityPolicy())
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

    override fun updateVariables(variables: Map<String, String>): RouteVariablesUpdateResult {
        val updated = variables.map { (name, newValue) ->
            variableStates[name]?.setValue(newValue) ?: return@map false
        }.any { it }

        return if (updated) RouteVariablesUpdateResult.UPDATED else RouteVariablesUpdateResult.NOT_UPDATED
    }
}
