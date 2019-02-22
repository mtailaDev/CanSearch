package com.example.cansearch.trial.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrialSummaryItem(val summaryTitle: String, val summaryValue: String, val highlight: Boolean) : Parcelable