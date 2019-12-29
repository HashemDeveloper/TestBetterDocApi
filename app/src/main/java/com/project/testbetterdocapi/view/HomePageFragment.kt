package com.project.testbetterdocapi.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.project.testbetterdocapi.R
import com.project.testbetterdocapi.di.Injectable
import dagger.android.support.AndroidSupportInjection

/**
 * A simple [Fragment] subclass.
 */
class HomePageFragment : Fragment(), Injectable {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}
