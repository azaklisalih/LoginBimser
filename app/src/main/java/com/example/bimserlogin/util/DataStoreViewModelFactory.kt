package com.example.bimserlogin.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bimserlogin.repository.DataStorePreferenceRepository
import com.example.bimserlogin.viewModel.LanguageViewModel

public class DataStoreViewModelFactory(private val dataStorePreferenceRepository: DataStorePreferenceRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LanguageViewModel::class.java)) {
            return LanguageViewModel(dataStorePreferenceRepository) as T
        }
        throw IllegalStateException()
    }

}