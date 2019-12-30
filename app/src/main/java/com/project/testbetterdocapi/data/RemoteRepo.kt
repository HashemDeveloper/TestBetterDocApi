package com.project.testbetterdocapi.data

import androidx.lifecycle.LiveData
import com.project.testbetterdocapi.models.BetterDocSearchByDiseaseRes
import com.project.testbetterdocapi.utils.ResultHandler
import com.project.testbetterdocapi.utils.resultLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RemoteRepo @Inject constructor(): IRemoteApiHelper, BaseDataSourceHandler() {
    @Inject
    lateinit var api: IBetterDocApi

    override suspend fun searchDocByDiseaseKtx(
        searchType: String,
        userKey: String,
        limit: Int,
        location: String,
        disease: String,
        sort: String
    ):ResultHandler<BetterDocSearchByDiseaseRes> {
        return getResult {
            this.api.searchDocByDiseaseKtx(searchType, userKey, limit, location, disease, sort)
        }
    }
}