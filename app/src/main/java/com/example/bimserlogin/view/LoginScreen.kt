package com.example.bimserlogin.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.bimserlogin.R
import com.example.bimserlogin.model.LoginRequest
import com.example.bimserlogin.navigation.Screen
import com.example.bimserlogin.viewModel.LoginViewModel


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    var loginRequest = remember{viewModel.loginRequest.value}

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val resource = painterResource(id = R.drawable.background)
        Image(
            painter = resource,
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //MultiLanguage()
            Spacer(modifier = Modifier.height(50.dp))
            IconSynergy()
            LoginBoxScreen(loginRequest, viewModel, navController)
        }
        SettingsButton(navController = navController)
    }
}

@Composable
fun SettingsButton(navController: NavController) {
    val resource = painterResource(id = R.drawable.ic_settings)

    Box(
        modifier = Modifier.size(10.dp, 10.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        IconButton(onClick = {
            navController.navigate(Screen.Server.route)
            {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        })
        {
            Icon(painter = resource, contentDescription = "")
        }
    }
}

@Composable
fun LoginButton(
    viewModel: LoginViewModel,
    navController: NavController,
    loginRequest: LoginRequest
) {
    val context = LocalContext.current
    // val loginResponse = remember {viewModel.loginResponse.value}
    Button(
        onClick = {
            viewModel.loadLogin(loginRequest, navController, context)
        },
        modifier = Modifier
            .size(100.dp, 50.dp),

        ) {

        Text(
            text = stringResource(id = R.string.login),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun IconSynergy() {
    val painter = painterResource(id = R.drawable.ic_synergy)
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(32.dp)) {
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()) {
            Image(painter = painter, contentDescription = "synergy", modifier = Modifier)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Bimser", fontWeight = FontWeight.Bold,fontSize = 24.sp)
            Spacer(modifier = Modifier.width(1.dp))
            Text(text = "Synergy", fontWeight = FontWeight.Light, fontSize = 24.sp)
        }
    }
}
