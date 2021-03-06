package com.project.testbetterdocapi.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.project.testbetterdocapi.models.InsurancePlan
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InsuranceProvider(
    @SerializedName("uid")
    @Expose
    var uid: String,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("plans")
    @Expose
    var plansList: List<InsurancePlan>
): Parcelable