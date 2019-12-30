package com.project.testbetterdocapi.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.project.neardoc.di.viewmodel.ViewModelFactory

import com.project.testbetterdocapi.R
import com.project.testbetterdocapi.di.Injectable
import com.project.testbetterdocapi.models.BetterDocSearchByDiseaseRes
import com.project.testbetterdocapi.models.Profile
import com.project.testbetterdocapi.viewmodels.HomePageViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomePageFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val homePageViewModel: HomePageViewModel by viewModels {
        this.viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.homePageViewModel.fetchData()
        this.homePageViewModel.fetchDocByDiseaseLiveData?.observe(activity!!, Observer {
            if (it.data is BetterDocSearchByDiseaseRes) {
                val data: BetterDocSearchByDiseaseRes = it.data
                for (docList in data.searchByDiseaseData) {
                    val profile: Profile = docList.profile
                    Log.d(TAG, profile.firstName)
                }
            }
        })
    }

    companion object {
        @JvmStatic private val TAG = HomePageFragment::class.java.canonicalName!!
    }
}
