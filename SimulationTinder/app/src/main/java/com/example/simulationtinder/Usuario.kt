package com.example.simulationtinder

import android.os.Parcel
import android.os.Parcelable

class Usuario(
    var nombre : String?,
    var celular : String?,
    var universidad : String?,
    var edad :  Int? ):Parcelable

{
    override fun toString(): String {
        return "Nombre: ${nombre} edad: ${edad} celular: ${celular} universidad: ${universidad}"
    }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(celular)
        parcel.writeString(universidad)
        parcel.writeValue(edad)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }


}