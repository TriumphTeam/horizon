package dev.triumphteam.horizon.state

import dev.triumphteam.horizon.component.Component
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
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

    protected var _value: T = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return _value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this._value = value
        update()
    }

    override fun toString(): String {
        return "[$_value]"
    }
}

@Serializable(with = RouteVariable.Serializer::class)
public class RouteVariable(initialValue: String) : SimpleMutableState<String>(initialValue) {

    public object Serializer : KSerializer<RouteVariable> {

        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("RouteVariable", PrimitiveKind.STRING)

        override fun serialize(
            encoder: Encoder,
            value: RouteVariable,
        ) {
            encoder.encodeString(value._value)
        }

        override fun deserialize(decoder: Decoder): RouteVariable {
            val value = decoder.decodeString()
            return RouteVariable(value)
        }
    }
}
