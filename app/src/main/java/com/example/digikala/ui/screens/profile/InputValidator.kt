package com.example.digikala.ui.screens.profile

import androidx.core.text.isDigitsOnly

object InputValidator {

    fun phoneValidator(phoneInput: String): Boolean{
        return phoneInput.isNotEmpty()
                && phoneInput.isNotBlank()
                && phoneInput.isDigitsOnly()
                && phoneInput.startsWith("09")
                && phoneInput.length == 11
    }

    fun emailValidator(email: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun passwordValidator(password: String): Boolean{
        return password.isNotEmpty()
                && password.isNotBlank()
                && password.length > 5
    }

}