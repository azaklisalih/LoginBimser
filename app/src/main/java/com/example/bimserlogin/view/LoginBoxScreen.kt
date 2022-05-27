package com.example.bimserlogin.view

import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bimserlogin.R
import com.example.bimserlogin.model.LoginRequest
import com.example.bimserlogin.viewModel.LoginViewModel

private lateinit var prefence: SharedPreferences
private lateinit var editor: SharedPreferences.Editor

@Composable
fun LoginBoxScreen(
    loginRequest: LoginRequest,
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {

    var idValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var focusRequester = remember { FocusRequester() }

    loginRequest.captcha = null
    loginRequest.captchaId = null
    loginRequest.password = ""
    loginRequest.language = stringResource(id = R.string.language)
    loginRequest.rememberMe = false
    loginRequest.username = ""
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .height(250.dp), contentAlignment = Alignment.Center
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {

            OutlinedTextField(
                value = idValue,
                onValueChange = {
                    idValue = it
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                label = { Text(text = stringResource(id = R.string.username)) },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.username),
                        color = Color.LightGray
                    )
                },
                modifier = Modifier.fillMaxWidth(0.8f),
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = passwordValue,
                onValueChange = {
                    passwordValue = it
                },
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_password_eye),
                            tint = if (passwordVisibility) Color.Black else Color.Gray,
                            contentDescription = ""
                        )
                    }
                },
                label = { Text(text = stringResource(id = R.string.password)) },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.password),
                        color = Color.LightGray
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                visualTransformation = if (passwordVisibility) VisualTransformation.None
                else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .focusRequester(focusRequester = focusRequester),
                //keyboardActions = {KeyboardActions(onDone = {focusManager.clearFocus()})},
                //keyboardActions = { KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password)}
            )
            Spacer(modifier = Modifier.height(10.dp))

            Switch(
                checked = false, enabled = false,
                onCheckedChange = {
                    //loginRequest.rememberMe = it
                },
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(30.dp, 0.dp),
            )
        }
        loginRequest.password = passwordValue
        loginRequest.username = idValue
    }
    LoginButton(viewModel = viewModel, navController = navController, loginRequest)
}

