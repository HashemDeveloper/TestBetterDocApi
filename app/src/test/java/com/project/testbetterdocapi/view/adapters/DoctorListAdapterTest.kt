package com.project.testbetterdocapi.view.adapters

import android.app.Application
import android.content.Context
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.project.testbetterdocapi.models.Profile
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [21])
class DoctorListAdapterTest {
    private var sut: DoctorListAdapter?= null
    private val data: MutableList<Profile> = arrayListOf()
    private var context: Context?= null

    @Before
    fun setup() {
        val application: Application = RuntimeEnvironment.application
        this.context
        this.sut = DoctorListAdapter(this.context!!)
        val profile1 = Profile("Yulia", "Fadieieva", "124 East ave", "MD", "image2", "Female", arrayListOf(), "YuliaBio")
        val profile2 = Profile("Irina", "Kogan", "124 west ave", "MD", "image1", "Female", arrayListOf(), "koganBio")
        val profile3 = Profile("Jean", "Brice", "124 north ave", "MD", "image3", "Male", arrayListOf(), "jeanBio")
        this.data.add(profile1)
        this.data.add(profile2)
        this.data.add(profile3)
        this.sut!!.setData(this.data)
    }

    @Test
    fun onCreateViewHolder() {
        val parent = LinearLayout(this.context)
        val holder: DoctorListAdapter.DoctorListViewHolder = this.sut!!.onCreateViewHolder(parent, 0) as DoctorListAdapter.DoctorListViewHolder
        assertTrue(holder is DoctorListAdapter.DoctorListViewHolder)
    }

    @Test
    fun getItemCount() {
    }

    @Test
    fun onBindViewHolder() {
    }

    @Test
    fun setData() {
    }
}