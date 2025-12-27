@file:Suppress("NOTHING_TO_INLINE")

package dev.triumphteam.horizon.state

import dev.triumphteam.horizon.component.Component
import kotlin.reflect.KProperty

public inline fun <T> mutableStateOf(initialValue: T): MutableState<T> = SimpleMutableState(initialValue)

public interface State<T> {

    public operator fun getValue(thisRef: Any?, property: KProperty<*>): T
}

public interface MutableState<T> : State<T> {

    public operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T)
}

public abstract class AbstractMutableState<T> : MutableState<T> {

    private val listeners: MutableMap<Component, () -> Unit> = mutableMapOf()

    internal fun addListener(component: Component, listener: () -> Unit) {
        println("Adding listener")
        listeners[component] = listener
    }

    internal fun removeListener(component: Component) {
        listeners.remove(component)
    }

    protected fun update() {
        println("Updating state!")
        println("Currently have ${listeners.size} listeners.")
        listeners.forEach { (_, listener) -> listener() }
    }
}

public open class SimpleMutableState<T>(initialValue: T) : AbstractMutableState<T>() {

    internal var value: T = initialValue
        private set

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        setValue(value)
    }

    internal fun setValue(value: T) {
        this.value = value
        update()
    }

    override fun toString(): String {
        return "[$value]"
    }
}
