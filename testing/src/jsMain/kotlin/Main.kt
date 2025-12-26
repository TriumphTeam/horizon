package dev.triumphteam.horizon

import dev.triumphteam.horizon.component.component
import dev.triumphteam.horizon.html.tag.a
import dev.triumphteam.horizon.html.tag.br
import dev.triumphteam.horizon.html.tag.button
import dev.triumphteam.horizon.html.tag.div
import dev.triumphteam.horizon.router.navigate
import kotlinx.browser.window

public fun main() {
    app {

        route("example") {
            div {
                text("Rendering example route")

                /*onClick = {
                    window.alert("Kotlin!")
                }*/
            }

            navigate(to = "/example2") {
                text("Example 2!")
            }
        }


        route("example2") {
            div {
                className = "test"

                text("Rendering example route 2")

                /*onClick = { event ->
                    window.alert("Kotlin!")
                }*/
            }

            navigate(to = "/example") {
                text("Example!")
            }

            br()

            navigate(to = "/with-variable/example") {
                text("Example with variable!")
            }
        }

        route("with-variable/:variable") {
            div {
                text("You are on route with variable: ")
            }

            br()

            navigate(to = "../example") {
                text("Example!")
            }

            br()

            navigate(to = "../another") {
                text("Another!")
            }

            br()

            a {
                href = "#example"
                text("Example!")
            }

            br()

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

        /* routes<Test> {

         }*/
        println("hello-world")
    }
}

