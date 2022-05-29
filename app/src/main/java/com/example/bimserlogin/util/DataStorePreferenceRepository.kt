package com.example.bimserlogin.util

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.intellij.lang.annotations.Language
import javax.inject.Inject
import javax.inject.Singleton

@ViewModelScoped
class DataStorePreferenceRepository @Inject constructor(
  @ApplicationContext  context: Context) {

    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "Settings" )
    private val defaultLanguage = 1
    private val defaultUsername = ""
    private val defaultPassword = ""
    private val defaultServerUrl = "https://release.bimser.net/"

    companion object{
        val PREF_LANGUAGE = intPreferencesKey("language")
        val PREF_USERNAME = stringPreferencesKey("username")
        val PREF_PASSWORD = stringPreferencesKey("password")
        val PREF_SERVER_URL = stringPreferencesKey("serverurl")

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
    suspend fun setServerUrl(serverUrl: String,context: Context){
        context.dataStore.edit { preferences ->
            preferences[PREF_SERVER_URL] = serverUrl
        }
    }
    val getServerUrl : Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PREF_SERVER_URL] ?: defaultServerUrl
        }
}