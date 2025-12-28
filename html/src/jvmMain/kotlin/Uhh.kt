package dev.triumphteam.horizon.html

import dev.triumphteam.horizon.html.tag.body
import dev.triumphteam.horizon.html.tag.div
import dev.triumphteam.horizon.html.tag.head
import dev.triumphteam.horizon.html.tag.html
import kotlin.math.min

public fun main() {

    val mine = createHtml {
        html {
            lang = "en"

            head {
                /*link {
                    rel = "stylesheet"
                    href = "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
                }
                title {
                    text("My title")
                }*/
            }
            body {
                div {
                    className = "example"

                    text("before div")

                    div(className = "another") {
                       /* img {
                            src = "https://via.placeholder.com/150"
                            alt = "Placeholder"
                            width = 150
                            height = 150
                        }

                        input {
                            required = true
                            placeholder = "Placeholder"
                        }*/

                        text("another")
                    }

                    text("after div")
                }
            }
        }
    }

    println(HtmlStringRenderer(true, mine).render())
    // println("Equal: ${mine == hmm}")
}
