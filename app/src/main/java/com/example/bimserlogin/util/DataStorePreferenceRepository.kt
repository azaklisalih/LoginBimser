package com.example.bimserlogin.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.intellij.lang.annotations.Language

class DataStorePreferenceRepository(context : Context) {
    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "LanguageData" )
    private val defaultLanguage = 1
    private val defaultUsername = ""
    private val defaultPassword = ""

    companion object{
        val PREF_LANGUAGE = intPreferencesKey("language")
        val PREF_USERNAME = stringPreferencesKey("username")
        val PREF_PASSWORD = stringPreferencesKey("password")
        private var INSTANCE : DataStorePreferenceRepository? = null

        fun getInstance(context: Context) : DataStorePreferenceRepository{
            return  INSTANCE ?: synchronized(this){
                INSTANCE?.let {
                    return it
                }
                val instance = DataStorePreferenceRepository(context)
                INSTANCE = instance
                instance
            }
        }
    }
    suspend fun setLanguage(language: Int,context: Context){
         context.dataStore.edit { preferences ->
             preferences[PREF_LANGUAGE] = language
         }
    }

    val getLanguage : Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[PREF_LANGUAGE] ?: defaultLanguage
        }

    suspend fun setUsername(username: String,context: Context){
        context.dataStore.edit { preferences ->
            preferences[PREF_USERNAME] = username
        }
    }
    val getUsername : Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PREF_USERNAME] ?: defaultUsername
        }

    suspend fun setPassword(password: String,context: Context){
        context.dataStore.edit { preferences ->
            preferences[PREF_PASSWORD] = password
        }
    }
    val getPassword : Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PREF_PASSWORD] ?: defaultPassword
        }
}