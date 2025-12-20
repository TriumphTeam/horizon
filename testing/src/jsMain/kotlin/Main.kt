package dev.triumphteam.horizon

import dev.triumphteam.horizon.annotation.Route
import dev.triumphteam.horizon.component.component
import dev.triumphteam.horizon.router.navigate
import kotlinx.browser.window
import kotlinx.html.a
import kotlinx.html.button
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.js.br
import kotlinx.html.js.onClickFunction

public suspend fun main() {
    app {

        route<ExampleRoute> { route ->
            render {
                div {
                    +"Rendering example route"

                    onClickFunction = { event ->
                        window.alert("Kotlin!")
                    }
                }

                navigate(to = "/example2") {
                    text("Example 2!")
                }
            }
        }

        route<ExampleRoute2> { route ->
            render {
                div {
                    +"Rendering example route 2"
                    onClickFunction = { event ->
                        window.alert("Kotlin!")
                    }

                    div {

                    }

                    classes = setOf("test")
                }

                navigate(to = "/example") {
                    text("Example!")
                }
                br()
                navigate(to = "/with-variable/example") {
                    text("Example with variable!")
                }
            }
        }

        route<RouteWithVariable> { route ->
            render {
                div {
                    +"You are on route with variable: ${route.variable}"
                }

                navigate(to = "../example") {
                    text("Example!")
                }
                br()
                navigate(to = "../another") {
                    text("Another!")
                }
                a {
                    href = "#example"
                    text("Example!")
                }
                div {
                    component {

                        var clicks by remember(0)

                        render {
                            div {
                                text("Clicks: $clicks")
                            }
                            button {
                                onClickFunction = { clicks++ }
                                text("Click me!")
                            }
                        }
                    }
                }
            }
        }

        /* routes<Test> {

         }*/

        println("hello-world")
    }
}

@Route("/example")
public object ExampleRoute

@Route("/example2")
public object ExampleRoute2

@Route("/with-variable")
public data class RouteWithVariable(public val variable: String)
