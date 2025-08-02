import kotlinx.html.div
import kotlinx.html.stream.createHTML

public fun main() {

    val hmm = createHTML().div {
        div {
            +"Hello World!"
        }
        div {
            +"Hello World 2!"
        }
    }

    println(hmm)
}
