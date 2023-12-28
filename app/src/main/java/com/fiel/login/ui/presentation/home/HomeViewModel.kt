package com.fiel.login.ui.presentation.home


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fiel.login.ui.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val authUseCase: AuthUseCase):ViewModel() {

        var email=authUseCase.getCurrentUser()?.email

        fun logout(){
                authUseCase.logout()
        }
}