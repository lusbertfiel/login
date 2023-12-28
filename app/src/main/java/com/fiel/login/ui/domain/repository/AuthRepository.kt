package com.fiel.login.ui.domain.repository

import com.fiel.login.ui.domain.model.DataResponse
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser:FirebaseUser?
    suspend fun login(email:String,password:String):DataResponse<FirebaseUser>
    suspend fun register(email:String,password: String):DataResponse<FirebaseUser>
    fun logout()
}