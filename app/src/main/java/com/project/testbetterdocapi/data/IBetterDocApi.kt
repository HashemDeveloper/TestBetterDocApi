package com.project.testbetterdocapi.data

import com.project.testbetterdocapi.models.BetterDocSearchByDiseaseRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

interface IBetterDocApi {
    @Headers("Content-Type: application/json")
    @GET
    suspend fun searchDocByDiseaseKtx(@Url url: String, @Query("user_key") userKey: String, @Query("limit")
    limit: Int, @Query("location") location: String, @Query("query") disease: String, @Query("sort") sort: String): Response<BetterDocSearchByDiseaseRes>
}