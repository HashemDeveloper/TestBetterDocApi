package com.project.testbetterdocapi.view.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.neardoc.di.viewmodel.ViewModelFactory

import com.project.testbetterdocapi.R
import com.project.testbetterdocapi.di.Injectable
import com.project.testbetterdocapi.models.BetterDocSearchByDiseaseRes
import com.project.testbetterdocapi.models.Profile
import com.project.testbetterdocapi.utils.ResultHandler
import com.project.testbetterdocapi.view.adapters.DoctorListAdapter
import com.project.testbetterdocapi.viewmodels.HomePageViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home_page.*
import javax.inject.Inject

class HomePageFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val homePageViewModel: HomePageViewModel by viewModels {
        this.viewModelFactory
    }
    private var doctorListAdapter: DoctorListAdapter?= null
    private val docList: MutableList<Profile> = arrayListOf()

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
        fragment_home_page_recycler_view_id.layoutManager = LinearLayoutManager(this.context)
        this.doctorListAdapter = DoctorListAdapter(this.context!!)
        fragment_home_page_recycler_view_id.adapter = this.doctorListAdapter
        this.homePageViewModel.fetchData()
        this.homePageViewModel.fetchDocByDiseaseLiveData?.observe(activity!!, resultObserver())
    }

    private fun resultObserver(): Observer<ResultHandler<BetterDocSearchByDiseaseRes?>> {
        return Observer {
            when (it.status) {
                ResultHandler.ResultStatus.LOADING -> {
                    load(true)
                }
                ResultHandler.ResultStatus.SUCCESS -> {
                    load(false)
                    if (it.data is BetterDocSearchByDiseaseRes) {
                        val data: BetterDocSearchByDiseaseRes = it.data
                        for (docList in data.searchByDiseaseData) {
                            val profile: Profile = docList.profile
                            this.docList.add(profile)
                        }
                        this.doctorListAdapter?.setData(this.docList)
                    }
                }
                ResultHandler.ResultStatus.ERROR -> {
                    load(false)
                }
            }
        }
    }
    private fun load(b: Boolean) {
        fragment_home_page_progressId.visibility = if (b) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this.homePageViewModel.fetchDocByDiseaseLiveData != null) {
            this.homePageViewModel.fetchDocByDiseaseLiveData?.removeObserver(resultObserver())
        }
    }

    companion object {
        @JvmStatic private val TAG = HomePageFragment::class.java.canonicalName!!
    }
}
