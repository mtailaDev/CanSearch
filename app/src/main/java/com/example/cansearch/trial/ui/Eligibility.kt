package com.example.cansearch.trial.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Eligibility(val eligibilityCriteria: MutableList<TrialEligibilityItem>): Parcelable