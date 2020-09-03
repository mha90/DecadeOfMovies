package me.mhabulazm.decadeofmovies.model

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    val title: String,
    val year: Int,
    val rating: Int,
    val cast: List<String>?,
    val genres: List<String>?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(year)
        parcel.writeInt(rating)
        parcel.writeStringList(cast)
        parcel.writeStringList(genres)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}