package com.example.digikala.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.dataStore.DataStoreRepository
import com.example.digikala.utils.Constants.PERSIAN_LANG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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

    fun getUserLanguage(): String = runBlocking {
        repository.getString(USER_LANGUAGE_KEY) ?: PERSIAN_LANG
    }

    fun saveUserPhone(phone: String){
        viewModelScope.launch {
            repository.putString(USER_PHONE_KEY, phone)
        }
    }
    fun getUserPhone(): String? = runBlocking {
        repository.getString(USER_PHONE_KEY)
    }
    fun saveUserId(id: String){
        viewModelScope.launch {
            repository.putString(USER_ID_KEY, id)
        }
    }
    fun getUserId(): String? = runBlocking {
        repository.getString(USER_ID_KEY)
    }
    fun saveUserToken(token: String){
        viewModelScope.launch {
            repository.putString(USER_TOKEN_KEY, token)
        }
    }
    fun getUserToken(): String? = runBlocking {
        repository.getString(USER_TOKEN_KEY)
    }
    fun saveUserPassword(password: String){
        viewModelScope.launch {
            repository.putString(USER_PASSWORD_KEY, password)
        }
    }
    fun getUserPassword(): String? = runBlocking {
        repository.getString(USER_PASSWORD_KEY)
    }


}