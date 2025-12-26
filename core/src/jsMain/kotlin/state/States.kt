package dev.triumphteam.horizon.state

import dev.triumphteam.horizon.component.Component
import kotlin.reflect.KProperty

public interface MutableState<T> {

    public operator fun getValue(thisRef: Any?, property: KProperty<*>): T

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

    protected var value: T = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
        update()
    }

    override fun toString(): String {
        return "[$value]"
    }
}
