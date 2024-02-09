package com.adia.dev.playground.data

import com.adia.dev.playground.data.model.LoggedInUser
import java.io.IOException

/**
 * Class that handles authentication w/ authentication credentials and retrieves user information.
 */
class AuthenticationDataSource {

    fun authentication(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}