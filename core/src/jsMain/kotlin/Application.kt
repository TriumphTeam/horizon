package dev.triumphteam.horizon

import dev.triumphteam.horizon.router.Route
import dev.triumphteam.horizon.router.RouteBlock
import dev.triumphteam.horizon.router.RouteProvider
import dev.triumphteam.horizon.router.Router
import dev.triumphteam.horizon.router.SimpleRoute
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.Element

public object Application {

    @PublishedApi
    internal val rootElement: Element = document.getElementById("root")
        ?: run {
            document.body?.innerHTML = "<h1>Couldn't find root element!</h1>"
            error("Couldn't find root! Application cannot run!")
        }

    @PublishedApi
    internal val router: Router = Router(rootElement)

    init {
        window.addEventListener(
            "popstate",
            {
                router.navigateTo(window.location.pathname, pushState = false)
            },
        )
    }

    public fun goTo(path: String) {
        router.navigateTo(path)
    }

    public fun index(block: RouteBlock<Route>) {
        router.index(block)
    }

    public fun route(path: String, block: RouteBlock<SimpleRoute>) {
        router.route(path, block)
    }

    public fun <T : Route> route(
        path: String,
        routeProvider: RouteProvider<T>,
        block: RouteBlock<T>,
    ) {
        router.route(path, routeProvider, block)
    }

    public fun notFound(block: RouteBlock<Route>) {
        router.route(Router.NOT_FOUND_ROUTE, block)
    }
}

public fun app(block: Application.() -> Unit) {
    block(Application)

    // Once we finish loading the application, we can navigate to the current URL.
    // We don't push state on this call, so we don't add 2 histories right at the beginning.
    Application.router.navigateTo(window.location.pathname, pushState = false)
}
