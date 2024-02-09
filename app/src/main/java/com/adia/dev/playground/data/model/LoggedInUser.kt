package com.adia.dev.playground.data.model

/**
 * Data class that captures user information for logged in users retrieved from AuthenticationRepository
 */
data class LoggedInUser(
    val userId: String,
    val displayName: String,
)