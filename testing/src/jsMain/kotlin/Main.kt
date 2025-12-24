package dev.triumphteam.horizon

import dev.triumphteam.horizon.annotation.Route
import dev.triumphteam.horizon.component.component
import dev.triumphteam.horizon.html.tag.a
import dev.triumphteam.horizon.html.tag.br
import dev.triumphteam.horizon.html.tag.button
import dev.triumphteam.horizon.html.tag.div
import dev.triumphteam.horizon.state.RouteVariable

public suspend fun main() {
    app {

        route<ExampleRoute> { route ->
            render {
                div {
                    text("Rendering example route")

                    /*onClick {
                        window.alert("Kotlin!")
                    }*/
                }

                /*navigate(to = "/example2") {
                    text("Example 2!")
                }*/
            }
        }

        route<ExampleRoute2> { route ->
            render {
                div {
                    className = "test"

                    text("Rendering example route 2")
                    /*onClickFunction = { event ->
                        window.alert("Kotlin!")
                    }*/

                    div {

                    }
                }

                /*navigate(to = "/example") {
                    text("Example!")
                }*/
                br()
                /*navigate(to = "/with-variable/example") {
                    text("Example with variable!")
                }*/
            }
        }

        route<RouteWithVariable> { route ->
            render {
                div {
                    text("You are on route with variable: ${route.variable}")
                }

                /*navigate(to = "../example") {
                    text("Example!")
                }*/
                br()
                /*navigate(to = "../another") {
                    text("Another!")
                }*/
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
                                onClick = {
                                    println("clicked!!!")
                                    clicks++
                                }
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
public data class RouteWithVariable(public val variable: RouteVariable)
