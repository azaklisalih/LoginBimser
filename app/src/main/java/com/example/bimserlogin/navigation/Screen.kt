package com.example.bimserlogin.navigation

sealed class Screen(val route : String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object Server : Screen("server_screen")
    object Login : Screen("login_screen")
}