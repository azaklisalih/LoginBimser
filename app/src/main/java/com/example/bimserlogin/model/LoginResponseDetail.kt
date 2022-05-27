package com.example.bimserlogin.model

data class LoginResponseDetail(
    val token : String?,
    val authCode : String?,
    val mfaEnabled : Boolean,
    val tempTokenKey : String?
)