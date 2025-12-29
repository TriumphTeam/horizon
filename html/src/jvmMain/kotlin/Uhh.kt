package dev.triumphteam.horizon.html

import dev.triumphteam.horizon.html.tag.div

public fun main() {

    val mine = createHtml {
        div {
            id = "parent-div"
        }
    }

    println(HtmlStringRenderer(true, mine).render())
    // println("Equal: ${mine == hmm}")
}
