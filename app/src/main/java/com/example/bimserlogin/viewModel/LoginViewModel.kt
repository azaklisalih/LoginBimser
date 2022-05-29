package com.example.bimserlogin.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.bimserlogin.model.LoginRequest
import com.example.bimserlogin.model.LoginResponse
import com.example.bimserlogin.navigation.Screen
import com.example.bimserlogin.repository.LoginRepository
import com.example.bimserlogin.util.DataStorePreferenceRepository
import com.example.bimserlogin.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val dataStorePreferenceRepository: DataStorePreferenceRepository
) : ViewModel() {

    var loginResponse = mutableStateOf<Resource<LoginResponse>>(Resource.Loading())
    var loginRequest = mutableStateOf<LoginRequest>(LoginRequest("","","",null,"",""))

    fun loadLogin(loginRequest: LoginRequest, navController: NavController, context: Context) {

        viewModelScope.launch {

            val result = repository.login(loginRequest)
            when (result) {
                is Resource.Success -> {
                    loginResponse.value = result

                    navController.navigate(Screen.Home.route)
                }
                is Resource.Error -> {
                    result.message
                    Toast.makeText(context, "Incorrect username or password!", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}