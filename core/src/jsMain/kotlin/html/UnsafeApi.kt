package dev.triumphteam.horizon.html

@RequiresOptIn(level = RequiresOptIn.Level.WARNING, message = "This api is unsafe and should be used with caution.")
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
public annotation class UnsafeApi
