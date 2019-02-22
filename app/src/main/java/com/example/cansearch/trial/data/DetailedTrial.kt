package com.example.cansearch.trial.data


import android.os.Parcelable
import com.example.cansearch.trial.ui.*
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailedTrial(
    val studySummary: StudySummary,
    val trialSummary: TrialSummary,
    val associatedDiseases: AssociatedDiseases,
    val associatedGenes: AssociatedGenes,
    val eligibility: Eligibility
) : Parcelable