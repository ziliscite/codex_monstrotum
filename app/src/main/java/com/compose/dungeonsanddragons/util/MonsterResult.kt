package com.compose.dungeonsanddragons.util

sealed class MonsterResult<out R> private constructor() {
    data class Success<out T>(val data: T) : MonsterResult<T>()
    data class Failed(val error: String) : MonsterResult<Nothing>()
    data object Loading : MonsterResult<Nothing>()
}
