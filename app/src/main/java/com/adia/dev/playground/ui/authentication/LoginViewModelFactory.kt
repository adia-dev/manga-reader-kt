package com.adia.dev.playground.ui.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adia.dev.playground.data.AuthenticationDataSource
import com.adia.dev.playground.data.AuthenticationRepository

/**
 * ViewModel provider factory to instantiate AuthenticationViewModel.
 * Required given AuthenticationViewModel has a non-empty constructor
 */
class AuthenticationViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthenticationViewModel::class.java)) {
            return AuthenticationViewModel(
                authenticationRepository = AuthenticationRepository(
                    dataSource = AuthenticationDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}