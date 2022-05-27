package com.example.bimserlogin.viewModel

import androidx.lifecycle.ViewModel
import com.example.bimserlogin.repository.LoginRepository
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val repository : LoginRepository
) : ViewModel() {
}