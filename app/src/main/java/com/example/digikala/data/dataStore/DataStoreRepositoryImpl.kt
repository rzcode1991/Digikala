package com.example.digikala.data.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.digikala.utils.AES
import com.example.digikala.utils.Constants.DATASTORE_NAME
import com.example.digikala.utils.Constants.IV
import com.example.digikala.utils.Constants.KEY
import kotlinx.coroutines.flow.first
import javax.inject.Inject


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)
class DataStoreRepositoryImpl @Inject constructor(
    private val context: Context
): DataStoreRepository {

    override suspend fun putString(key: String, value: String) {
        val encryptedValue = AES.encryptAES(value, KEY, IV)
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = encryptedValue
        }
    }

    override suspend fun putInt(key: String, value: Int) {
        val preferencesKey = intPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun getString(key: String): String? {
        return try {
            val preferencesKey = stringPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            preferences[preferencesKey]?.let { AES.decryptAES(it, KEY, IV) }
        }catch (e:Exception){
            e.printStackTrace()
            null
        }
    }

    override suspend fun getInt(key: String): Int? {
        return try {
            val preferencesKey = intPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            preferences[preferencesKey]
        }catch (e:Exception){
            e.printStackTrace()
            null
        }
    }
}