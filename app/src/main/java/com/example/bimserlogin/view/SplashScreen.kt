package com.example.bimserlogin.view

import android.view.animation.AlphaAnimation
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.bimserlogin.R
import com.example.bimserlogin.navigation.Screen
import com.example.bimserlogin.viewModel.SplashViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    //viewModel: SplashViewModel = hiltViewModel()
) {

    /*var startAnimation by remember { mutableStateOf(false)}
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
    animationSpec = tween(
        durationMillis = 3000
    ))*/

    LaunchedEffect(key1 = true){
        //startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(Screen.Login.route)


    }
    Surface(modifier = Modifier
        .fillMaxSize()) {
            Splash(navController)

    }
}
@Composable
@Preview
fun SplashScreenPreview(){

}
@Composable
fun Splash(navController: NavController){

    val resource = painterResource(id = R.drawable.index1)

       Box(modifier = Modifier.fillMaxSize()) {
           Image(painter = resource, contentDescription = "Bimser Index", modifier = Modifier.matchParentSize(), contentScale = ContentScale.FillBounds)
       }
}


