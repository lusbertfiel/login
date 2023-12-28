package com.fiel.login.ui.domain.model

import java.lang.Exception

sealed class DataResponse <out T>{
    data object Cargando:DataResponse<Nothing>()
    data class Success<out T>(val data:T):DataResponse<T>()
    data class Error<out T>(val e:Exception?):DataResponse<T>()
}