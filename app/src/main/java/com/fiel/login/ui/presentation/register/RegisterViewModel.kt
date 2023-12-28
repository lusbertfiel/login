package com.fiel.login.ui.presentation.register

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiel.login.ui.domain.model.DataResponse
import com.fiel.login.ui.domain.usecase.AuthUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authUseCase: AuthUseCase) :ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirPassword by mutableStateOf("")

    var emailValido by mutableStateOf(false)
    var passwordValido by mutableStateOf(false)
    var confirmPasswordValido by mutableStateOf(false)

    var visible by mutableStateOf(false)
    var visible2 by mutableStateOf(false)

    var enable by mutableStateOf(false)

    val currentUser =authUseCase.getCurrentUser()

    var stateRegister by mutableStateOf<DataResponse<FirebaseUser>?>(null)

    fun register()=viewModelScope.launch {
        stateRegister=DataResponse.Cargando
        stateRegister=authUseCase.registerUseCase(email, password)
    }

    fun valideEmail(){
        emailValido = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        valideButton()
    }

    fun validePassword(){
        passwordValido = password.isNotEmpty() && password.length>4
        valideButton()
    }
    fun valideConfirmPassword(){
        confirmPasswordValido = confirPassword.isNotEmpty() && confirPassword==password
        valideButton()
    }

    fun valideButton(){
        enable = emailValido&&passwordValido && confirmPasswordValido
    }
}