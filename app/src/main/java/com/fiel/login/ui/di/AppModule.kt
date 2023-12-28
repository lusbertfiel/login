package com.fiel.login.ui.di

import com.fiel.login.ui.data.AuthRepositoryImpl
import com.fiel.login.ui.domain.repository.AuthRepository
import com.fiel.login.ui.domain.usecase.AuthUseCase
import com.fiel.login.ui.domain.usecase.GetCurrentUser
import com.fiel.login.ui.domain.usecase.LoginUseCase
import com.fiel.login.ui.domain.usecase.Logout
import com.fiel.login.ui.domain.usecase.RegisterUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseAuth()=FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl:AuthRepositoryImpl):AuthRepository=impl

    @Provides
    fun provideAuthUseCase(repository: AuthRepository)=AuthUseCase(
        loginUseCase = LoginUseCase(repository),
        logout = Logout(repository),
        registerUseCase = RegisterUseCase(repository),
        getCurrentUser = GetCurrentUser(repository)
    )
}