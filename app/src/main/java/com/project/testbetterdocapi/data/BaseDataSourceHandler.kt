package com.project.testbetterdocapi.data

import android.util.Log
import com.project.testbetterdocapi.BuildConfig
import com.project.testbetterdocapi.utils.ResultHandler
import retrofit2.Response
import retrofit2.http.Body
import java.lang.Exception

open class BaseDataSourceHandler {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ResultHandler<T> {
        try {
            val response: Response<T> = call()
            if (response.isSuccessful) {
                val body: T?= response.body()
                return if (body != null) {
                    ResultHandler.success(body)
                } else {
                   ResultHandler.onError(null, "NetworkFailed because: ${response.code()}, ${response.body()}")
                }
            } else {
                return ResultHandler.onError(null, "NetworkFailed because: ${response.code()}, ${response.body()}")
            }
        } catch (ex: Exception) {
            if (ex.localizedMessage != null) {
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "Exception at networking: ${ex.localizedMessage!!}")
                }
            }
            return ResultHandler.onError(null, ex.localizedMessage!!)
        }
    }

    companion object {
        @JvmStatic private val TAG: String = BaseDataSourceHandler::class.java.canonicalName!!
    }
}