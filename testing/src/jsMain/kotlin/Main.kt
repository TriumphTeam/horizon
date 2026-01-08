package dev.triumphteam.horizon

import dev.triumphteam.horizon.html.br
import dev.triumphteam.horizon.html.div
import dev.triumphteam.horizon.router.navigate
import kotlinx.browser.window

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
            div(className = "test") {

                text("Rendering example route 2")

                onClick = { event ->
                    window.alert("Kotlin!")
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
                text("parent div")
            }
        }
    }
}

