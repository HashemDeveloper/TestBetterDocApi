package com.project.testbetterdocapi.data

import androidx.lifecycle.LiveData
import com.project.testbetterdocapi.utils.ResultHandler
import retrofit2.http.Query

interface IRemoteApiHelper {
    fun searchDocByDiseaseKtx(@Query("doctors?") searchType: String, @Query("user_key") userKey: String, @Query("limit")
    limit: Int, @Query("location") location: String, @Query("query") disease: String, @Query("sort") sort: String)
    fun getResultLiveData(): LiveData<ResultHandler<Any>>
}