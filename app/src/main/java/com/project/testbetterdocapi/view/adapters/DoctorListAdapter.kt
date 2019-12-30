package com.project.testbetterdocapi.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.project.testbetterdocapi.R
import com.project.testbetterdocapi.models.Profile
import com.project.testbetterdocapi.utils.GlideApp

class DoctorListAdapter constructor(private val context: Context): RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val data: MutableList<Profile> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view: View = LayoutInflater.from(this.context).inflate(R.layout.doctor_list_item_layout, parent, false)
        return DoctorListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val profile: Profile = this.data[position]
        profile.let {
            (holder as DoctorListViewHolder).bindView(profile)
        }
    }

    fun setData(data: List<Profile>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class DoctorListViewHolder constructor(private val view: View): BaseViewHolder<Profile>(view) {
        private var docImageView: AppCompatImageView?= null
        private var docNameView: AppCompatTextView?= null
        private var docBioView: AppCompatTextView?= null

        init {
            this.docImageView = this.view.findViewById(R.id.doctor_list_image_view_id)
            this.docNameView = this.view.findViewById(R.id.doctor_list_name_view_id)
            this.docBioView = this.view.findViewById(R.id.doctor_list_bio_view_id)
        }
        override fun bindView(item: Profile) {
            item.let {
                val name: String = it.firstName + " ${it.lastName}" + ", ${it.title}"
                this.docNameView?.text = name
                this.docBioView?.let {bioView ->
                    val description: String = if (it.bio.length > 100) {
                        it.bio.substring(0, 50) + "..."
                    } else {
                        it.bio
                    }
                    bioView.text = description
                }
                GlideApp.with(context)
                    .load(it.imageUrl)
                    .placeholder(R.drawable.ic_person_black_24dp)
                    .into(this.docImageView!!)
            }
        }
    }
}