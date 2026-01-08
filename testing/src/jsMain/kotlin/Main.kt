package dev.triumphteam.horizon

import dev.triumphteam.horizon.component.functional.component
import dev.triumphteam.horizon.html.a
import dev.triumphteam.horizon.html.br
import dev.triumphteam.horizon.html.button
import dev.triumphteam.horizon.html.div
import dev.triumphteam.horizon.router.navigate
import dev.triumphteam.horizon.state.mutableStateOf
import kotlinx.browser.window
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.seconds

public fun main() {
    app {

        route("example") {
            div {
                text("Rendering example route")

                onClick = {
                    window.alert("Kotlin!")
                }
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

                onClick = { event ->
                    window.alert("Kotlin!")
                }
            }

            navigate(to = "/example") {
                text("Example!")
            }

            // br()

            navigate(to = "/with-variable/example") {
                text("Example with variable!")
            }
        }

        route("with-variable/:variable") { route ->
            div(id = "parent-div") {
                id = "parent-div"
                component {
                    println("upper component")
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

                        val testState = mutableStateOf("text-red-400")

                        val div = div {
                            val testStateValue by testState
                            className = testStateValue
                            text("This is a reactive attribute")
                        }
                        br()
                        component {
                            val testStateValue by remember(testState)

                            render {

                                parentComponent.launch {
                                    delay(5.seconds)
                                    println("testStateValue: $testStateValue")
                                }

                                div.element.className = testStateValue
                            }
                        }
                        button {
                            var testStateValue by testState
                            onClick = {
                                testStateValue = "text-blue-400"
                            }

                            text("blue")
                        }
                        button {
                            var testStateValue by testState
                            onClick = {
                                testStateValue = "text-red-400"
                            }

                            text("red")
                        }
                        button {
                            var testStateValue by testState
                            onClick = {
                                testStateValue = "text-purple-400"
                            }

                            text("purple")
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

            println("child element: ${element.lastChild}")
            component {
                println("under component")
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
    }
}

