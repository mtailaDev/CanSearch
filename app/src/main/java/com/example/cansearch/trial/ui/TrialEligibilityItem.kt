package com.example.cansearch.trial.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrialEligibilityItem(val eligibilityTitle: String, val eligibilityValue: String): Parcelable