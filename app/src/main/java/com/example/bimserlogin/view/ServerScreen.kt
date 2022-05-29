@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.bimserlogin.view

import android.Manifest
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bimserlogin.R
import com.example.bimserlogin.navigation.Screen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@ExperimentalPermissionsApi
@Composable
fun ServerScreen(
    navController: NavController) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Gray
        ) {

            Column(verticalArrangement = Arrangement.Top) {
                TopAppBar(title = {
                    Text(
                        text = stringResource(id = R.string.servertitle),
                        fontSize = 20.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                })
                Spacer(modifier = Modifier.height(10.dp))
                val cameraPermissionState =
                    rememberPermissionState(permission = Manifest.permission.CAMERA)

                Button(
                    onClick = {
                        cameraPermissionState.launchPermissionRequest()
                        navController.navigate(Screen.QR.route)
                    }, modifier = Modifier
                        .size(150.dp, 50.dp)
                        .align(CenterHorizontally)
                ) {
                    Text(text = stringResource(id = com.example.bimserlogin.R.string.addserver ), fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Column(verticalArrangement = Arrangement.Bottom) {
                    ServerList("ss")

                }

            }

        }
    }

@Composable
fun ServerList(serverNick : String){
    LazyColumn{
        itemsIndexed(
            listOf("stage","release")
        ){index, string ->
            Text(
                text = string,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp))

        }
    }
}


/*@Composable
fun ServerList(servers : List<Servers> ,selectedServer: (Int) ->Unit){

    LazyColumn(contentPadding = PaddingValues(5.dp)){
        items(servers) {server ->
            ServerRow(navController = navController, server = server)
        }
    }

}
@Composable
fun ServerRow(server : ){

}*/