package com.example.cansearch.trial.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrialSummary(val summaryItems: MutableList<TrialSummaryItem>) : Parcelable