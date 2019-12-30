package com.project.testbetterdocapi.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

fun <T> resultLiveData(networkCall: suspend () -> ResultHandler<T>): LiveData<ResultHandler<T?>> =
    liveData(Dispatchers.IO) {
        emit(ResultHandler.onLoading<T>())
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == ResultHandler.ResultStatus.SUCCESS) {
            emit(ResultHandler.success(responseStatus.data))
        } else if (responseStatus.status == ResultHandler.ResultStatus.ERROR) {
            emit(ResultHandler.onError(null, responseStatus.message!!))
        }
    }