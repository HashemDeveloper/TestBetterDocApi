package com.project.testbetterdocapi.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.testbetterdocapi.BuildConfig
import com.project.testbetterdocapi.models.BetterDocSearchByDiseaseRes
import com.project.testbetterdocapi.utils.ResultHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RemoteRepo @Inject constructor(): IRemoteApiHelper, CoroutineScope {
    private val resultLiveData: MutableLiveData<ResultHandler<Any>> = MutableLiveData()
    @Inject
    lateinit var api: IBetterDocApi
    private val job: Job = Job()

    override fun searchDocByDiseaseKtx(
        searchType: String,
        userKey: String,
        limit: Int,
        location: String,
        disease: String,
        sort: String
    ){
        val resultHandler = ResultHandler
        launch {
            try {
                val result: Response<BetterDocSearchByDiseaseRes> = api.searchDocByDiseaseKtx(searchType, userKey, limit, location, disease, sort)
                if (result.isSuccessful) {
                    val body: BetterDocSearchByDiseaseRes = result.body()!!
                    resultLiveData.value = resultHandler.success(body)
                } else {
                    resultLiveData.value = resultHandler.onError(result.code(), result.message())
                }
            } catch (ex: Exception) {
                if (BuildConfig.DEBUG) {
                    ex.let {
                        Log.d(TAG, "Exception at fetching data: ${it.localizedMessage!!}")
                    }
                }
                resultLiveData.value = resultHandler.onError(null, ex.localizedMessage!!)
            }
        }
    }

    override fun getResultLiveData(): LiveData<ResultHandler<Any>> {
        return this.resultLiveData
    }

    override val coroutineContext: CoroutineContext
        get() = this.job + Dispatchers.IO

    companion object {
       @JvmStatic private val TAG: String = RemoteRepo::class.java.canonicalName!!
    }
}