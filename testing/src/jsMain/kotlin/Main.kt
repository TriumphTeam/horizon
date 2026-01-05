package dev.triumphteam.horizon

import dev.triumphteam.horizon.component.functional.component
import dev.triumphteam.horizon.component.functional.elementComponent
import dev.triumphteam.horizon.html.tag.a
import dev.triumphteam.horizon.html.tag.br
import dev.triumphteam.horizon.html.tag.button
import dev.triumphteam.horizon.html.tag.div
import dev.triumphteam.horizon.router.navigate

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

            navigate(to = "/with-variable/example") {
                text("Navigation to example with variable!")
            }
        }


        index {
            div {
                className = "test"

                text("Rendering example route 2")

                /*onClick = { event ->
                    window.alert("Kotlin!")
                }*/
            }

            elementComponent {

                render {

                }
            }

            navigate(to = "/example") {
                text("Example!")
            }

            br()

            navigate(to = "/with-variable/example") {
                text("Example with variable!")
            }
        }

        route("with-variable/:variable") { route ->
            div(id = "parent-div") {
                // id = "parent-div"
                component {

                    val variable by remember(route["variable"])

                    render {
                        div {
                            id = "bitch"
                            text("You are on route with variable: $variable")

                            div {
                                id = "deep-div"
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
                }
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
                text("Example anchor!")
            }

            navigate(to = "/example") {
                text("Navigate to example route!")
            }

            br()

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

            div {
                div {
                    text("bu hu")
                }
                text("buh")
            }
        }

        /* routes<Test> {

         }*/
        println("hello-world")
    }
}

