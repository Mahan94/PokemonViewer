package com.it.mahan.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PokemonStat(
    @SerializedName("base_stat")
    val baseStat: Int?,
    val effort: Int?,
    @SerializedName("stat")
    val statDetail: StatDetail?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readParcelable(StatDetail::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(baseStat)
        parcel.writeValue(effort)
        parcel.writeParcelable(statDetail, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonStat> {
        override fun createFromParcel(parcel: Parcel): PokemonStat {
            return PokemonStat(parcel)
        }

        override fun newArray(size: Int): Array<PokemonStat?> {
            return arrayOfNulls(size)
        }
    }
}