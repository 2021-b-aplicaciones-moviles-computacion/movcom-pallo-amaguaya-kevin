package com.example.istock

import android.os.Parcel
import android.os.Parcelable

class Categorias(
    var id_Categorias:String?,
    var nombre:String?,
):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_Categorias)
        parcel.writeString(nombre)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Categorias> {
        override fun createFromParcel(parcel: Parcel): Categorias {
            return Categorias(parcel)
        }

        override fun newArray(size: Int): Array<Categorias?> {
            return arrayOfNulls(size)
        }
    }


}