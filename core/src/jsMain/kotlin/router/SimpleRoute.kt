package dev.triumphteam.horizon.router

import dev.triumphteam.horizon.state.State

public interface Route {

    public fun updateVariables(variables: Map<String, String>): RouteVariablesUpdateResult
}

public interface SimpleRoute : Route {

    public operator fun get(name: String): State<String>

    public fun getVariable(name: String): String

    public fun getVariableNullable(name: String): String?
}

public enum class RouteVariablesUpdateResult {
    UPDATED, NOT_UPDATED, ERROR;
}
