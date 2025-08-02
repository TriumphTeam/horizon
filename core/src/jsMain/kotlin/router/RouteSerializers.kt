package dev.triumphteam.horizon.router

import dev.triumphteam.horizon.annotation.Route
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.elementNames
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer

@PublishedApi
internal inline fun <reified T> segments(): List<Segment> {
    return encodeToPathPattern(serializer<T>())
}

@PublishedApi
internal fun <T> encodeToPathPattern(serializer: KSerializer<T>): List<Segment> {

    val descriptor: SerialDescriptor = serializer.descriptor
    val path = descriptor.annotations.filterIsInstance<Route>().first().path

    return buildList {
        // First are the direct "exact" path from the annotation.
        path.trim('/').split('/').forEach {
            this += Segment(it, SegmentType.EXACT)
        }

        // Then add the variables as part of the path.
        descriptor.elementNames.forEachIndexed { index, elementName ->
            this += Segment(
                name = elementName,
                type = when {
                    descriptor.isElementOptional(index) -> SegmentType.VARIABLE_OPTIONAL
                    else -> SegmentType.VARIABLE
                },
            )
        }
    }
}

@OptIn(ExperimentalSerializationApi::class)
internal class RouteDecoder(
    private val segments: List<String>,
    override val serializersModule: SerializersModule = EmptySerializersModule(),
) : AbstractDecoder() {

    private var index: Int = 0

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        val elementCount = descriptor.elementsCount
        if (elementCount == 0) return CompositeDecoder.DECODE_DONE
        if (index >= elementCount) return CompositeDecoder.DECODE_DONE
        if (index >= segments.size) return CompositeDecoder.DECODE_DONE
        return index++
    }

    override fun decodeBoolean(): Boolean {
        return decodeString().toBoolean()
    }

    override fun decodeByte(): Byte {
        return decodeString().toByte()
    }

    override fun decodeChar(): Char {
        return decodeString()[0]
    }

    override fun decodeDouble(): Double {
        return decodeString().toDouble()
    }

    override fun decodeFloat(): Float {
        return decodeString().toFloat()
    }

    override fun decodeInt(): Int {
        return decodeString().toInt()
    }

    override fun decodeLong(): Long {
        return decodeString().toLong()
    }

    override fun decodeShort(): Short {
        return decodeString().toShort()
    }

    override fun decodeString(): String {
        return segments[index - 1]
    }

    override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
        TODO("Enum decoding is not yet supported")
    }
}
