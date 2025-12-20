package dev.triumphteam.horizon.html

import dev.triumphteam.horizon.html.tag.HTMLStringRenderer
import dev.triumphteam.horizon.html.tag.div
import kotlinx.html.a
import kotlinx.html.area
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.header
import kotlinx.html.stream.createHTML

public fun main() {

    val mine = HTMLStringRenderer(true, false)
        .apply {
            div(className = "example") {
                text("example")

                div(className = "another") {
                    text("another")
                }
            }
        }.render()

    println(mine)

    println("--------------------")

    val hmm = createHTML().div {
        classes = setOf("example")

        text("example")

        div {
            classes = setOf("another")
            text("another")
        }

        a {
            href = "#example"
            text("example")
        }

        header {

        }

        area { }
    }

    println(hmm)

    println("Equal: ${mine == hmm}")
}
