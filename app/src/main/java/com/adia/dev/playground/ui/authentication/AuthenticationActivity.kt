package com.adia.dev.playground.ui.authentication

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import com.adia.dev.playground.MainActivity
import com.adia.dev.playground.databinding.ActivityAuthenticationBinding

import com.adia.dev.playground.R

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var authenticationViewModel: AuthenticationViewModel
    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.username
        val password = binding.password
        val authentication = binding.authentication
        val loading = binding.loading

        authenticationViewModel = ViewModelProvider(this, AuthenticationViewModelFactory())
            .get(AuthenticationViewModel::class.java)

        authenticationViewModel.authenticationFormState.observe(
            this@AuthenticationActivity,
            Observer {
                val authenticationState = it ?: return@Observer

                // disable authentication button unless both username / password is valid
                authentication.isEnabled = authenticationState.isDataValid

                if (authenticationState.usernameError != null) {
                    username.error = getString(authenticationState.usernameError)
                }
                if (authenticationState.passwordError != null) {
                    password.error = getString(authenticationState.passwordError)
                }
            })

        authenticationViewModel.authenticationResult.observe(this@AuthenticationActivity, Observer {
            val authenticationResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (authenticationResult.error != null) {
                showAuthenticationFailed(authenticationResult.error)
            }
            if (authenticationResult.success != null) {
                updateUiWithUser(authenticationResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy authentication activity once successful
            finish()
        })

        username.afterTextChanged {
            authenticationViewModel.authenticationDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                authenticationViewModel.authenticationDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        authenticationViewModel.authentication(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            authentication.setOnClickListener {
                loading.visibility = View.VISIBLE
                authenticationViewModel.authentication(
                    username.text.toString(),
                    password.text.toString()
                )
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName

        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()

        val intent = Intent(this@AuthenticationActivity, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    private fun showAuthenticationFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}