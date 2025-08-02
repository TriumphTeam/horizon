package dev.triumphteam.horizon

import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.consumers.onFinalize
import kotlinx.html.div
import kotlinx.html.js.p

public class App {

    public fun route(path: String, ) {

    }
}

public fun app(block: App.() -> Unit) {
}

public fun main() {

    app {
        route(path = "/")
    }

    println(window.location.pathname)

    val root = document.getElementById("root") ?: error("Couldn't find root!")

    val elements = buildList {
        MyJSDOMBuilder(document).onFinalize { element, partial ->
            println("finalizing")
            if (!partial) add(element)
        }.apply {
            div {
                p { +"Bellow world!" }
            }
            div {
                p { +"Bellow world!" }
            }
            div {
                p { +"Bellow world!" }

                p {

                    test()

                    +"Bellow world!"
                }
            }
        }
    }

    println(elements)

    elements.forEach(root::appendChild)

    /*app {
        component {

            var clicks by remember(0)

            render {
                div {
                    p {
                        +"Clicks: $clicks"
                    }
                    button {
                        onClickFunction = { clicks++ }
                        +"Click me!"
                    }
                }
            }
        }
    }*/
}
