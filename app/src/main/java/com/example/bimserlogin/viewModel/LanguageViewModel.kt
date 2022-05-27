package com.example.bimserlogin.viewModel

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bimserlogin.util.DataStorePreferenceRepository
import kotlinx.coroutines.launch

class LanguageViewModel(
    private val dataStorePreferenceRepository: DataStorePreferenceRepository
) : ViewModel() {
    private val _language = MutableLiveData(1)
    var language: LiveData<Int> = _language

    init {
        viewModelScope.launch {
            dataStorePreferenceRepository.getLanguage.collect {
                _language.value = it
            }
        }
    }

    suspend fun saveLanguage(language: Int,context: Context) {
        dataStorePreferenceRepository.setLanguage(language,context)
    }
}