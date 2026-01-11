@file:Suppress("NOTHING_TO_INLINE")

package dev.triumphteam.horizon.state

import dev.triumphteam.horizon.component.ReactiveElement
import dev.triumphteam.horizon.state.policy.StateMutationPolicy
import dev.triumphteam.horizon.state.policy.StructureEqualityPolicy
import kotlin.reflect.KProperty

public interface State<T> {

    public operator fun getValue(thisRef: Any?, property: KProperty<*>): T
}

public interface MutableState<T> : State<T> {

    public operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T)
}

public abstract class AbstractState<T> : State<T> {

    private val listeners: MutableMap<ReactiveElement, () -> Unit> = mutableMapOf()

    @PublishedApi
    internal fun addListener(element: ReactiveElement, listener: () -> Unit) {
        listeners[element] = listener
    }

    @PublishedApi
    internal fun removeListener(element: ReactiveElement) {
        listeners.remove(element)
    }

    protected fun update() {
        listeners.forEach { (_, listener) -> listener() }
    }
}

public open class SimpleMutableState<T>(initialValue: T, private val mutationPolicy: StateMutationPolicy<T>) :
    AbstractState<T>(), MutableState<T> {

    internal var value: T = initialValue
        private set

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        setValue(value)
    }

    internal fun setValue(value: T): Boolean {
        val shouldMutate = mutationPolicy.shouldMutate(this.value, value)

        if (!shouldMutate) return false

        this.value = value
        update()
        return true
    }

    override fun toString(): String {
        return "[$value]"
    }
}

public inline fun <T> mutableStateOf(
    initialValue: T,
    mutationPolicy: StateMutationPolicy<T> = StructureEqualityPolicy(),
): MutableState<T> = SimpleMutableState(initialValue, mutationPolicy)
