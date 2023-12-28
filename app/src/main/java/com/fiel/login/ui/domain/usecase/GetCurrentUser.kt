package com.fiel.login.ui.domain.usecase

import com.fiel.login.ui.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(private val repository: AuthRepository) {
    operator fun  invoke()=repository.currentUser
}