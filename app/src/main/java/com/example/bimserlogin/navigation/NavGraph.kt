package com.example.bimserlogin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bimserlogin.view.LoginScreen
import com.example.bimserlogin.view.ServerScreen
import com.example.bimserlogin.view.SplashScreen
import com.example.bimserlogin.view.HomeScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SetupNavGraph(navController : NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){
      composable(route = Screen.Splash.route){
         SplashScreen(navController)
      }
        composable(route = Screen.Login.route){
            LoginScreen(navController)
        }
        composable(route = Screen.Server.route){
            ServerScreen(navController)
        }
        composable(route = Screen.Home.route){
            HomeScreen(navController)
        }
    }

}