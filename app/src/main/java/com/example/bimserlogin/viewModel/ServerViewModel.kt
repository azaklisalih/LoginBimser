package com.example.bimserlogin.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bimserlogin.repository.DataStorePreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServerViewModel @Inject constructor(
    private val dataStorePreferenceRepository: DataStorePreferenceRepository
) : ViewModel(){

    private val _serverUrl = MutableLiveData("")
    var serverUrl : LiveData<String> = _serverUrl

    init {
        viewModelScope.launch {
            dataStorePreferenceRepository.getServerUrl.collect{
                _serverUrl.value = it
            }
        }
    }

    suspend fun saveServerUrl(serverUrl : String, context: Context){
        dataStorePreferenceRepository.setServerUrl(serverUrl,context)
    }

}
