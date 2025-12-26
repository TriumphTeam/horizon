package dev.triumphteam.horizon

import dev.triumphteam.horizon.router.RouteBlock
import dev.triumphteam.horizon.router.Router
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
                println("Changed url -> ${window.location.pathname}")
            },
        )
    }

    public fun goTo(path: String) {
        println("Going to $path")
        router.navigateTo(path)
    }

    public fun index(block: RouteBlock) {

    }

    public fun route(path: String, block: RouteBlock) {
        router.route(path, block)
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
