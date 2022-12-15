package com.example.projectg12.models

import android.os.Parcel
import android.os.Parcelable

enum class MenuCategory {
    Pasta,
    Pizza,
    Desserts,
    Appetizers
}

data class MenuItem(
    var category: String? = null,
    var description: String? = null,
    var name: String? = null,
    var price: Double? = null,
) : Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<MenuItem> {
        override fun createFromParcel(parcel: Parcel): MenuItem {
            return MenuItem(parcel)
        }

        override fun newArray(size: Int): Array<MenuItem?> {
            return arrayOfNulls(size)
        }
    }
}
