package dev.triumphteam.horizon

import dev.triumphteam.horizon.Application.route
import dev.triumphteam.horizon.component.functional.component
import dev.triumphteam.horizon.html.br
import dev.triumphteam.horizon.html.button
import dev.triumphteam.horizon.html.div
import dev.triumphteam.horizon.html.p
import dev.triumphteam.horizon.router.navigate
import kotlinx.browser.window

public fun main() {
    app {
        route(path = "docs/:versionProject?/:projectPage?/:page?") { route ->
            val versionProject by route["versionProject"]
            val projectPage by route["projectPage"]
            val page by route["page"]

            p {
                text("Version: $versionProject Project: $projectPage Page: $page")
            }
        }

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
            component {
                var open by remember(false)

                render {
                    div {
                        onClick = { open = !open }

                        if (open) div { text("X") } else div { text("-") }
                    }
                }
            }

            div(className = "test") {

                text("Rendering example route 2")

                onClick = { event ->
                    window.alert("Kotlin!")
                }
            }

            component {
                var open by remember(false)

                render {
                    div {
                        onClick = { open = !open }

                        if (open) div { text("X") } else div { text("-") }
                    }
                }
            }

            component {

                var clicks by remember(0)

                render {
                    div { text("1") }

                    component {
                        println("Creating the culprit component!")
                        render { div { text("2") } }
                    }

                    div { text("3") }
                    div(className = "change-$clicks") {
                        text("This should remain unchanged!")
                    }

                    component {
                        println("Creating the second culprit component!")
                        render { div { text("5") } }
                    }

                    div { text("6") }

                    button {
                        onClick = { clicks++ }
                        text("Click me")
                    }
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

