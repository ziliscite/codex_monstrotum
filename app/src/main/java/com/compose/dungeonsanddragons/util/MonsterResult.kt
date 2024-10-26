package com.compose.dungeonsanddragons.util

sealed class MonsterResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : MonsterResult<T>(data)
    class Failed<T>(message: String) : MonsterResult<T>(message = message)
    class Loading<T> : MonsterResult<T>()
}
