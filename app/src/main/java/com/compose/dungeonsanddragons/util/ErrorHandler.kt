package com.compose.dungeonsanddragons.util

import com.google.gson.JsonParseException
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

// Common error handling logic
fun Exception.loadErrorResult(): Exception {
    return when (this) {
        is IOException -> Exception("No network found—seek the path anew.")
        is HttpException -> Exception("The server dragon roars: ${this.code()}.")
        is TimeoutException -> Exception("The portal timed out... check your connection.")
        is JsonParseException -> Exception("Try refreshing to decipher the runes.")
        is NoSuchElementException -> Exception(this.message ?: "No monsters found")
        else -> Exception("An unexpected fate: ${this.localizedMessage ?: "Unknown error"}")
    }
}

// Retry logic with a configurable delay and retry count
internal suspend fun <T> retryCall(
    retryCount: Int = 5,
    delayMillis: Long = 1000,
    block: suspend () -> T
): T {
    repeat(retryCount - 1) {
        try {
            return block()
        } catch (e: IOException) {
            delay(delayMillis)
        } catch (e: TimeoutException) {
            delay(delayMillis)
        }
    }
    return block() // Last attempt without catching exceptions
}
