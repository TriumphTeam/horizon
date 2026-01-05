package dev.triumphteam.horizon.component.functional

import dev.triumphteam.horizon.state.MutableState
import dev.triumphteam.horizon.state.SimpleMutableState
import dev.triumphteam.horizon.state.State
import dev.triumphteam.horizon.state.policy.StateMutationPolicy
import dev.triumphteam.horizon.state.policy.StructureEqualityPolicy

public interface StateHolder {

    public fun <T> remember(
        initialValue: T,
        mutationPolicy: StateMutationPolicy<T> = StructureEqualityPolicy(),
    ): MutableState<T>

    public fun <T> remember(state: State<T>): State<T>

    public fun <T> remember(state: MutableState<T>): MutableState<T>
}

@PublishedApi
internal abstract class AbstractStateHolder : StateHolder {
    private val states = mutableListOf<State<*>>()

    override fun <T> remember(initialValue: T, mutationPolicy: StateMutationPolicy<T>): MutableState<T> {
        return SimpleMutableState(initialValue, mutationPolicy).also(states::add)
    }

    override fun <T> remember(state: State<T>): State<T> = state.also(states::add)

    override fun <T> remember(state: MutableState<T>): MutableState<T> = state.also(states::add)

    @PublishedApi
    internal fun getStates(): List<State<*>> = states.toList()
}
