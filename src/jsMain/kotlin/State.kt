package dev.triumphteam.horizon

import kotlin.reflect.KProperty

public interface MutableState<T> {

    public operator fun getValue(thisRef: Any?, property: KProperty<*>): T

    public operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T)

    public fun addListener(listener: () -> Unit)
}

public class SimpleState<T>(private val initialState: T) : MutableState<T> {

    private val listeners = mutableListOf<() -> Unit>()
    private var _value: T = initialState

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return _value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        _value = value
        update()
    }

    override fun addListener(listener: () -> Unit) {
        listeners += listener
    }

    public fun update() {
        listeners.forEach { it() }
    }
}
