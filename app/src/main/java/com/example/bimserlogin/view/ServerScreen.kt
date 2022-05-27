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
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import androidx.navigation.NavController
import com.example.bimserlogin.util.Constants.BASE_URL
import com.example.bimserlogin.util.QRScanner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.common.util.concurrent.ListenableFuture
import org.json.JSONObject
import org.json.JSONTokener
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

private lateinit var prefence: SharedPreferences
private lateinit var editor: SharedPreferences.Editor

@ExperimentalPermissionsApi
@Composable
fun ServerScreen(
    navController: NavController,
) {

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
                }, modifier = Modifier
                    .size(150.dp, 50.dp)
                    .align(CenterHorizontally)
            ) {
                Text(text = "Ekle", fontSize = 24.sp)
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            CameraPreview()
        }
    }
}

@Composable
fun CameraPreview() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var preview by remember { mutableStateOf<Preview?>(null) }
    val barCodeVal = remember { mutableStateOf("") }

    AndroidView(
        factory = { AndroidViewContext ->
            PreviewView(AndroidViewContext).apply {
                this.scaleType = PreviewView.ScaleType.FILL_CENTER
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                )
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
            }
        },
        modifier = Modifier.size(400.dp, 400.dp),
        update = { previewView ->
            val cameraSelector: CameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()
            val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()
            val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> =
                ProcessCameraProvider.getInstance(context)

            cameraProviderFuture.addListener({
                preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                val barcodeAnalyser = QRScanner { barcodes ->
                    barcodes.forEach { barcode ->
                        barcode.rawValue?.let { barcodeValue ->
                            barCodeVal.value = barcodeValue
                            Toast.makeText(context, barcodeValue, Toast.LENGTH_SHORT).show()
                            println(barcodeValue)
                            jsonParser(barcodeValue, context)
                        }
                    }
                }
                val imageAnalysis: ImageAnalysis = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(cameraExecutor, barcodeAnalyser)
                    }

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageAnalysis
                    )
                } catch (e: Exception) {
                    Log.d("TAG", "CameraPreview: ${e.localizedMessage}")
                }
            }, ContextCompat.getMainExecutor(context))
        }
    )
}

fun jsonParser(barcodeval: String, context: Context) {

    prefence = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    val jsonObject = JSONTokener(barcodeval).nextValue() as JSONObject

    val serverNick = jsonObject.getString("serverNick")
    Log.i("Server Nick:", serverNick)

    var serverAddress = jsonObject.getString("serverAddress")
    Log.i("Server Adress:", serverAddress)

    val domainAddress = jsonObject.getString("domainAddress")
    Log.i("domainAddress:", domainAddress)

    val serverMainPath = jsonObject.getString("serverMainPath")
    Log.i("Server Nick:", serverMainPath)

    serverAddress += "/api/"
    serverAddress
    println(serverAddress)
    prefence.edit().putString(serverAddress, "BASE_URL_2")
    println(prefence.getString("BASE_URL_2", BASE_URL))

}