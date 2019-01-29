package com.example.cansearch.login.ui

import android.os.Parcel
import android.os.Parcelable


data class OnboardingUI(val backgroundGradient: Int, val title: String?, val description: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(backgroundGradient)
        parcel.writeString(title)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OnboardingUI> {
        override fun createFromParcel(parcel: Parcel): OnboardingUI {
            return OnboardingUI(parcel)
        }

        override fun newArray(size: Int): Array<OnboardingUI?> {
            return arrayOfNulls(size)
        }
    }
}