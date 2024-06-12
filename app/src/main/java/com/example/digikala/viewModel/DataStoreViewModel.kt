package com.example.digikala.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.dataStore.DataStoreRepository
import com.example.digikala.utils.Constants.PERSIAN_LANG
import com.example.digikala.utils.Constants.USER_ID
import com.example.digikala.utils.Constants.USER_PASSWORD
import com.example.digikala.utils.Constants.USER_PHONE
import com.example.digikala.utils.Constants.USER_TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val repository: DataStoreRepository
): ViewModel() {

    companion object{
        const val USER_LANGUAGE_KEY = "USER_LANGUAGE_KEY"
        const val USER_PHONE_KEY = "USER_PHONE_KEY"
        const val USER_ID_KEY = "USER_ID_KEY"
        const val USER_TOKEN_KEY = "USER_TOKEN_KEY"
        const val USER_PASSWORD_KEY = "USER_PASSWORD_KEY"
    }

    fun saveUserLanguage(language: String){
        viewModelScope.launch {
            repository.putString(USER_LANGUAGE_KEY, language)
        }
    }

    fun getUserLanguage(): String {
        var userLanguage = PERSIAN_LANG
        viewModelScope.launch {
            userLanguage = repository.getString(USER_LANGUAGE_KEY) ?: PERSIAN_LANG
        }
        return userLanguage
    }

    fun saveUserPhone(phone: String){
        viewModelScope.launch {
            repository.putString(USER_PHONE_KEY, phone)
        }
    }

    fun getUserPhone(): String {
        var userPhone = USER_PHONE
        viewModelScope.launch {
            userPhone = repository.getString(USER_PHONE_KEY) ?: USER_PHONE
        }
        return userPhone
    }

    fun saveUserId(id: String){
        viewModelScope.launch {
            repository.putString(USER_ID_KEY, id)
        }
    }

    fun getUserId(): String {
        var userId = USER_ID
        viewModelScope.launch {
            userId = repository.getString(USER_ID_KEY) ?: USER_ID
        }
        return userId
    }

    fun saveUserToken(token: String){
        viewModelScope.launch {
            repository.putString(USER_TOKEN_KEY, token)
        }
    }

    fun getUserToken(): String {
        var userToken = USER_TOKEN
        viewModelScope.launch {
            userToken = repository.getString(USER_TOKEN_KEY) ?: USER_TOKEN
        }
        return userToken
    }

    fun saveUserPassword(password: String){
        viewModelScope.launch {
            repository.putString(USER_PASSWORD_KEY, password)
        }
    }

    fun getUserPassword(): String {
        var userPassWord = USER_PASSWORD
        viewModelScope.launch {
            userPassWord = repository.getString(USER_PASSWORD_KEY) ?: USER_PASSWORD
        }
        return userPassWord
    }

    fun clearDataStore(){
        viewModelScope.launch {
            try {
                saveUserToken("")
                saveUserPhone("")
                saveUserId("USER_ID")
                saveUserPassword("")
            } catch (e: Exception) {
                Log.e("my_tag", "clearDataStore error: ${e.message}")
            }
        }
    }


}