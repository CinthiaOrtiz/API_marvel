package com.example.marvel.models

import android.os.Parcel
import android.os.Parcelable

data class moviesParcelable(val thumbnail: Int, val name: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(thumbnail)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<moviesParcelable> {
        override fun createFromParcel(parcel: Parcel): moviesParcelable {
            return moviesParcelable(parcel)
        }

        override fun newArray(size: Int): Array<moviesParcelable?> {
            return arrayOfNulls(size)
        }
    }
}