package dev.triumphteam.horizon.router

import dev.triumphteam.horizon.component.ReactiveComponent
import dev.triumphteam.horizon.component.Component
import dev.triumphteam.horizon.component.EmptyComponent
import dev.triumphteam.horizon.component.FunctionalComponent
import dev.triumphteam.horizon.component.SimpleFunctionalComponent
import dev.triumphteam.horizon.state.AbstractMutableState
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.serialization.KSerializer
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

    @PublishedApi
    internal var currentRoute: DecodedRoute? = null

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

        handleRoute(route, pathSegments)
    }

    private fun handleRoute(route: Route, pathSegments: List<String>) {
        val decodedRoute = when (val action = route.action) {
            is RoutedComponentRouteAction<*> -> handleRoutedComponentRouteAction(
                route = route,
                action = action,
                segments = pathSegments,
            )

            else -> error("Unsupported route action!")
        }

        println("Going to check if routes are the same.")

        val currentRoute = this.currentRoute
        if (currentRoute != null && currentRoute.route == route && currentRoute.routeObject == decodedRoute.routeObject) {
            // Found the same route, so we don't update anything.
            println("same route!!!")
            return
        }

        // Unmount current route so new one can take its place.
        currentRoute?.unmount()

        // We have a new route, so we need to set it as current.
        this.currentRoute = decodedRoute

        // Then render it to the dom.
        decodedRoute.render()
    }

    private fun handleRoutedComponentRouteAction(
        route: Route,
        action: RoutedComponentRouteAction<*>,
        segments: List<String>,
    ): DecodedRoute {
        // We need to remove segments that aren't route variables.
        val pathCount = route.segments.count { it.type == SegmentType.EXACT }
        val leftOverSegments = segments.drop(pathCount)

        // Then decode the route into its route object.
        val routeObject = RouteDecoder(leftOverSegments).decodeSerializableValue(action.serializer)
            ?: error("Failed to decode route data!")

        // Invoke the component function.
        val functionalComponent = SimpleFunctionalComponent()
        action.block.invoke(functionalComponent, routeObject.asDynamic())

        val states = functionalComponent.getStates()

        val component = ReactiveComponent(
            parent = EmptyComponent,
            boundNode = rootElement,
            render = functionalComponent.getComponentRender(),
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

        return DecodedRoute(
            route = route,
            routeObject = routeObject,
            component = component,
        )
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

internal data class DecodedRoute(
    internal val route: Route,
    internal val routeObject: Any,
    internal val component: Component,
) {

    internal fun unmount() {
        component.unmount()
    }

    internal fun render() {
        component.render()
    }
}
