package dev.triumphteam.horizon.state.policy

public sealed interface StateMutationPolicy<T> {

    public fun shouldMutate(currentValue: T?, newValue: T?): Boolean
}

public class StructureEqualityPolicy<T> : StateMutationPolicy<T> {
    override fun shouldMutate(currentValue: T?, newValue: T?): Boolean = currentValue != newValue
}

public class ReferenceEqualityPolicy<T> : StateMutationPolicy<T> {
    override fun shouldMutate(currentValue: T?, newValue: T?): Boolean = currentValue !== newValue
}

public class NeverEqualPolicy<T> : StateMutationPolicy<T> {
    override fun shouldMutate(currentValue: T?, newValue: T?): Boolean = true
}

public fun <T> neverEqual(): StateMutationPolicy<T> = NeverEqualPolicy()
public fun <T> structureEquality(): StateMutationPolicy<T> = StructureEqualityPolicy()
public fun <T> referenceEquality(): StateMutationPolicy<T> = ReferenceEqualityPolicy()
