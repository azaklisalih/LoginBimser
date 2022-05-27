package com.example.bimserlogin.service

import com.example.bimserlogin.model.LoginRequest
import com.example.bimserlogin.model.LoginResponse
import com.example.bimserlogin.model.LoginResponseDetail
import retrofit2.http.*

interface LoginAPI {

    //https://release.bimser.net/api/web/Login/Login
    //https://release.bimser.net/api/
    //https://release.bimser.net/api/web/Login/Login

    @Headers("bimser-encrypted-data:m9jz99/ep9h/McVKRzgtq3NuX0NPdEiOmCr2pm2GPfzPlInEt7ZfT8yEHq0UA5ZfnCEFH6gMlKfW82X+OGtlIMC6at4fwlwfaB37gcq7ZB7h+v0RQgVxm4uzzkmPY6CxxII6wHWH6jHWQgwEHeWtni3Gc6luwO8k8JBjSXgK7SQsdQMLl665cTeF+6NcQ69z","Accept:application/json, text/plain, */*","Content-Type:application/json;charset=UTF-8","Sec-Fetch-Mode:cors","contentType:application/json")
    @POST("web/Login/Login")
    suspend fun login(
        @Body requestBody: LoginRequest) : LoginResponse
}