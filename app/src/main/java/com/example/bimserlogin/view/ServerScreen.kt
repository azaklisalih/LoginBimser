@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.bimserlogin.view

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bimserlogin.navigation.Screen
import com.example.bimserlogin.util.Constants.BASE_URL
import com.example.bimserlogin.util.DataStorePreferenceRepository
import com.example.bimserlogin.util.DataStoreViewModelFactory
import com.example.bimserlogin.util.QRScanner
import com.example.bimserlogin.viewModel.LanguageViewModel
import com.example.bimserlogin.viewModel.LoginViewModel
import com.example.bimserlogin.viewModel.ServerViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.common.util.concurrent.ListenableFuture
import org.json.JSONObject
import org.json.JSONTokener
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

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
                        text = "Sunucu Listesi",
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
                    Text(text = "Ekle", fontSize = 24.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Column(verticalArrangement = Arrangement.Bottom) {
                    ServerList(){
                        println(it)
                    }
                }

            }

        }
    }
@Composable
fun ServerList(selectedServer: (Int) ->Unit){

    LazyColumn(modifier = Modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Bottom){
        items(5){
            Surface(modifier = Modifier.clickable{
                selectedServer(it+1)

            }) {
                Text(
                    text = "${it}",
                    fontSize = 36.sp,
                    modifier = Modifier.padding(8.dp)
                )
                Divider(color = Color.Gray, thickness = 1.dp)
            }

        }
    }

}