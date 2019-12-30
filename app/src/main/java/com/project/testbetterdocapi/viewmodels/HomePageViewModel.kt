package com.project.testbetterdocapi.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.project.testbetterdocapi.R
import com.project.testbetterdocapi.data.IRemoteApiHelper
import com.project.testbetterdocapi.models.BetterDocSearchByDiseaseRes
import com.project.testbetterdocapi.utils.ResultHandler
import com.project.testbetterdocapi.utils.resultLiveData
import javax.inject.Inject

class HomePageViewModel @Inject constructor(): ViewModel() {
    @Inject
    lateinit var context: Context
    @Inject
    lateinit var iRemoteApiHelper: IRemoteApiHelper
    var fetchDocByDiseaseLiveData: LiveData<ResultHandler<BetterDocSearchByDiseaseRes?>>?= null

    fun fetchData() {
        val apiKey: String = this.context.getString(R.string.better_doc_api_key)
        val location = "40.646420,-74.398643,10"
        this.fetchDocByDiseaseLiveData = resultLiveData {
            this.iRemoteApiHelper.searchDocByDiseaseKtx("doctors", apiKey, 10, location, "", "distance-asc")
        }
    }
}