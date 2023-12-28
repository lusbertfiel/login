package com.fiel.login.ui.data

import com.fiel.login.ui.domain.model.DataResponse
import com.fiel.login.ui.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private  val auth:FirebaseAuth):AuthRepository {
    override val currentUser: FirebaseUser?
        get() = auth.currentUser

    override suspend fun login(email: String, password: String): DataResponse<FirebaseUser> {
        return try {
            val res=auth.signInWithEmailAndPassword(email,password).await()
            DataResponse.Success(res.user!!)

        }catch (e:Exception){
          DataResponse.Error(e)
        }
    }

    override suspend fun register(email: String, password: String): DataResponse<FirebaseUser> {
        return try {
            val res=auth.createUserWithEmailAndPassword(email,password).await()
            DataResponse.Success(res.user!!)

        }catch (e:Exception){
            DataResponse.Error(e)
        }
    }

    override fun logout() {
      auth.signOut()
    }
}