package com.example.cansearch.trial.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AssociatedGenes(val associatedGenes: MutableList<String>) : Parcelable