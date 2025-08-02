package dev.triumphteam.horizon.router

import dev.triumphteam.horizon.Application.rootElement
import dev.triumphteam.horizon.component.CachedComponent
import dev.triumphteam.horizon.component.EmptyComponent
import dev.triumphteam.horizon.component.FunctionalComponent
import dev.triumphteam.horizon.component.SimpleFunctionalComponent
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.Entities
import kotlinx.serialization.KSerializer
import kotlinx.serialization.modules.EmptySerializersModule
import org.w3c.dom.Element

@PublishedApi
internal sealed interface RouteAction

@PublishedApi
internal data class RoutedComponentRouteAction<T>(
    internal val serializer: KSerializer<T>,
    internal val block: FunctionalComponent.(T) -> Unit,
) : RouteAction

@PublishedApi
internal data class NestedRouteAction(val component: () -> Unit) : RouteAction

@PublishedApi
internal class Router(private val rootElement: Element) {

    private var indexRoute: Route? = null
    private var notFoundRoute: Route? = null

    private val routes: MutableList<Route> = mutableListOf()

    private var currentRoute: Route? = null

    @PublishedApi
    internal fun route(route: Route) {
        this.routes += route
    }

    internal fun navigateTo(path: String, routes: List<Route> = this.routes) {
        // Trim out leading and trailing characters.
        val trimmedPath = path.trim().removePrefix("/").removePrefix("/")
        val pathSegments = if (trimmedPath.isEmpty()) emptyList() else trimmedPath.split("/")

        // TODO, prolly needs to be changed.
        window.history.pushState(null, document.title, path)

        if (pathSegments.isEmpty()) {
            println("Using index route, $indexRoute")
            return
        }

        val route = routes.find { matchRoute(pathSegments, it) }

        if (route == null) {
            println("No route found, using not found route, $notFoundRoute")
            // window.history.pushState(null, document.title, "/404")
            return
        }

        println("Found route, $route")
        when (val action = route.action) {
            is RoutedComponentRouteAction<*> -> handleRoutedComponentRouteAction(
                route = route,
                action = action,
                segments = pathSegments,
            )

            else -> {}
        }
    }

    private fun handleRoutedComponentRouteAction(
        route: Route,
        action: RoutedComponentRouteAction<*>,
        segments: List<String>,
    ) {
        val pathCount = route.segments.count { it.type == SegmentType.EXACT }
        val leftOverSegments = segments.drop(pathCount)

        val routeData = RouteDecoder(leftOverSegments).decodeSerializableValue(action.serializer)
        val functionalComponent = SimpleFunctionalComponent()
        action.block.invoke(functionalComponent, routeData.asDynamic())


        // TODO: CACHING AND SHIT.
        CachedComponent(
            parent = EmptyComponent,
            boundNode = rootElement,
            render = functionalComponent.getComponentRender(),
        ).render()
    }

    private fun matchRoute(pathSegments: List<String>, route: Route): Boolean {
        // To make sure we allow the path segments to be smaller than the url only when it has optionals.
        val routeSegments = route.segments.filter { it.type != SegmentType.VARIABLE_OPTIONAL }

        if (pathSegments.size < routeSegments.size) {
            return false
        }

        return pathSegments.zip(routeSegments).all { (urlSeg, routeSeg) ->
            when (routeSeg.type) {
                SegmentType.EXACT -> urlSeg == routeSeg.name
                SegmentType.VARIABLE -> urlSeg.isNotEmpty()
                else -> false
            }
        }
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
internal data class Route(
    internal val segments: List<Segment>,
    internal val isIndex: Boolean = false,
    internal val action: RouteAction,
)
