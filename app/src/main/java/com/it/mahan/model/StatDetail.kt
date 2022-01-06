package com.it.mahan.model

import android.os.Parcel
import android.os.Parcelable

data class StatDetail(
    val name: String?,
    val url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StatDetail> {
        override fun createFromParcel(parcel: Parcel): StatDetail {
            return StatDetail(parcel)
        }

        override fun newArray(size: Int): Array<StatDetail?> {
            return arrayOfNulls(size)
        }
    }
}