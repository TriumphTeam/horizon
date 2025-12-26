package dev.triumphteam.horizon.annotation

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.MetaSerializable
import kotlinx.serialization.SerialInfo

@SerialInfo
@MetaSerializable
@OptIn(ExperimentalSerializationApi::class)
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPEALIAS)
public annotation class RoutePath(public val path: String)
