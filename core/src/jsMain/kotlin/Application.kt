package dev.triumphteam.horizon

import dev.triumphteam.horizon.component.FunctionalComponent
import dev.triumphteam.horizon.router.Route
import dev.triumphteam.horizon.router.RoutedComponentRouteAction
import dev.triumphteam.horizon.router.Router
import dev.triumphteam.horizon.router.segments
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.serialization.serializer
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
        window.addEventListener("popstate", {
            println("Changed url -> ${window.location.pathname}")
        })
    }

    public fun goTo(path: String) {
        router.navigateTo(path)
    }

    public fun index(block: FunctionalComponent.() -> Unit) {

    }

    public inline fun <reified T> route(noinline block: FunctionalComponent.(T) -> Unit) {
        router.route(
            Route(
                segments = segments<T>(),
                action = RoutedComponentRouteAction(serializer<T>(), block),
            )
        )
        // TODO, move this
        /*CachedComponent(
            parent = EmptyComponent,
            boundNode = rootElement,
            render = SimpleFunctionalComponent().apply(block).getComponentRender(),
        ).render()*/
    }

    /*public inline fun <reified T> routes(block: NestedRoute.(T) -> Unit) {
        val segments = segments<T>()
    }*/

    public fun notFound(block: () -> Unit) {}
}

public fun app(block: Application.() -> Unit) {
    block(Application)

    // Once we finish loading the application, we can navigate to the current URL.
    Application.router.navigateTo(window.location.pathname)
}
