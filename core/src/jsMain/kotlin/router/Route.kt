package dev.triumphteam.horizon.router

import dev.triumphteam.horizon.state.State

public interface Route {

    public operator fun get(name: String): State<String>

    public fun getVariable(name: String): String

    public fun getVariableNullable(name: String): String?
}
