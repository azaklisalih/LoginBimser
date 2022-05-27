package com.example.bimserlogin.repository

import com.example.bimserlogin.model.LoginRequest
import com.example.bimserlogin.model.LoginResponse
import com.example.bimserlogin.service.LoginAPI
import com.example.bimserlogin.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class LoginRepository @Inject constructor(
    private val api: LoginAPI
) {
    suspend fun login(loginrequest: LoginRequest): Resource<LoginResponse> {
        try {
            val response =
                api.login(loginrequest)

            return Resource.Success(response)
        } catch (e: Exception) {

            return Resource.Error("Error")
        }
    }
}
