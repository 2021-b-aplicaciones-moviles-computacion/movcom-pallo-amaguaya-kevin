package com.example.istock

import android.os.Parcel
import android.os.Parcelable

class Productos(
    var id_Productos:String?,
    var nombre:String?,
    var valorUnitario:Float?,
    var cantidad:Int?
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_Productos)
        parcel.writeString(nombre)
        parcel.writeValue(valorUnitario)
        parcel.writeValue(cantidad)
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