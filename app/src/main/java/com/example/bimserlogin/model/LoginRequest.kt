package com.example.bimserlogin.model

data class LoginRequest(
    var language: String?,
    var username: String?,
    var password: String?,
    var rememberMe: Boolean?,
    var captcha: String?,
    var captchaId: String?
)