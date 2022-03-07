package com.example.istock

import android.os.Parcel
import android.os.Parcelable

class Productos(
    var id_Productos:String?,
    var nombre:String?,
    var valorUnitario:String?,
    var cantidad:String?,
    var enlace:String?
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_Productos)
        parcel.writeString(nombre)
        parcel.writeString(valorUnitario)
        parcel.writeString(cantidad)
        parcel.writeString(enlace)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Productos> {
        override fun createFromParcel(parcel: Parcel): Productos {
            return Productos(parcel)
        }

        override fun newArray(size: Int): Array<Productos?> {
            return arrayOfNulls(size)
        }
    }


}