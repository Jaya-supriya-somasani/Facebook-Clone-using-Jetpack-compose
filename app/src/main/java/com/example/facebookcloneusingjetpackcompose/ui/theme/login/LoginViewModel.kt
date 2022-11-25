package com.example.facebookcloneusingjetpackcompose.ui.theme.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    val userNameFlow = MutableStateFlow("")
    val userNameFlowError = MutableStateFlow(false)

    val passwordFlow = MutableStateFlow("")
    val passwordFlowError = MutableStateFlow(false)

    fun updateUserName(username: String) {
        userNameFlow.value = username
    }

    fun validateUserFields() {
        userNameFlowError.value = (userNameFlow.value.length < 5)
        passwordFlowError.value =(passwordFlow.value.length<5)
    }
}