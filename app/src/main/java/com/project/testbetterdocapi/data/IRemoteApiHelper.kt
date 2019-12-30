package com.project.testbetterdocapi.data

import androidx.lifecycle.LiveData
import com.project.testbetterdocapi.models.BetterDocSearchByDiseaseRes
import com.project.testbetterdocapi.utils.ResultHandler

interface IRemoteApiHelper {
    suspend fun searchDocByDiseaseKtx(searchType: String, userKey: String,
    limit: Int, location: String, disease: String, sort: String): ResultHandler<BetterDocSearchByDiseaseRes>
}