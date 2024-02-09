package com.adia.dev.playground.ui.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.adia.dev.playground.data.AuthenticationRepository
import com.adia.dev.playground.data.Result

import com.adia.dev.playground.R

class AuthenticationViewModel(private val authenticationRepository: AuthenticationRepository) : ViewModel() {

    private val _authenticationForm = MutableLiveData<AuthenticationFormState>()
    val authenticationFormState: LiveData<AuthenticationFormState> = _authenticationForm

    private val _authenticationResult = MutableLiveData<AuthenticationResult>()
    val authenticationResult: LiveData<AuthenticationResult> = _authenticationResult

    fun authentication(username: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = authenticationRepository.authentication(username, password)

        if (result is Result.Success) {
            _authenticationResult.value =
                AuthenticationResult(success = LoggedInUserView(displayName = result.data.displayName))
        } else {
            _authenticationResult.value = AuthenticationResult(error = R.string.authentication_failed)
        }
    }

    fun authenticationDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _authenticationForm.value = AuthenticationFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _authenticationForm.value = AuthenticationFormState(passwordError = R.string.invalid_password)
        } else {
            _authenticationForm.value = AuthenticationFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}