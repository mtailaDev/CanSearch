package com.example.cansearch.trial.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StudySummary(
    val briefTitle: String,
    val briefDescription: String,
    val scientificTitle: String,
    val scientificDescription: String
) : Parcelable