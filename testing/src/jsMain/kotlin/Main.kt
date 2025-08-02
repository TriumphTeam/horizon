package dev.triumphteam.horizon

import dev.triumphteam.horizon.annotation.Route
import kotlinx.browser.window
import kotlinx.html.a
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

                a {
                    href = "/example2"
                    +"Go to example 2!"

                    onClickFunction = { event ->
                        event.preventDefault()
                        println("Uh")
                        Application.goTo("/example2")
                    }
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
                }

                a {
                    href = "/example"
                    +"Go to example!"

                    onClickFunction = { event ->
                        event.preventDefault()
                        println("Uh")
                        Application.goTo("/example")
                    }
                }
            }
        }

        route<RouteWithVariable> { route ->
            render {
                div {
                    +"You are on route with variable: ${route.variable}"
                }

                a {
                    href = "/example"
                    +"Go to variable: example!"

                    onClickFunction = { event ->
                        event.preventDefault()
                        println("Uh")
                        Application.goTo("/example")
                    }
                }
                br()
                a {
                    href = "/example"
                    +"Go to variable: another!"

                    onClickFunction = { event ->
                        event.preventDefault()
                        println("Uh")
                        Application.goTo("/another")
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
