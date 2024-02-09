package com.adia.dev.playground.ui.authentication

/**
 * Data validation state of the authentication form.
 */
data class AuthenticationFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false,
)