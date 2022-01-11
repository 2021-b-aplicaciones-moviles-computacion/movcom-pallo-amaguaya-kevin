package com.example.appsmoviles

import android.os.Parcel
import android.os.Parcelable

class BEntrenador(
    val nombre: String?,
    val descripcion: String?
    // val numeros: Unit = null,)
    ):Parcelable {

    override fun toString(): String {
        return "Nombre: ${nombre} Descripción: ${descripcion}"
    }

    constructor(parcel: Parcel): this(
        parcel.readString(),
        parcel.readString()
    ){

    }
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(nombre)
        p0?.writeString(descripcion)
    } //Sirve para poder enviar en el intentExtraPut ya que no acepta objetos

    //********************************************************************

    companion object CREATOR : Parcelable.Creator<BEntrenador> {
        override fun createFromParcel(parcel: Parcel): BEntrenador {
            return BEntrenador(parcel)
        }

    override fun newArray(size: Int): Array<BEntrenador?> {
        return arrayOfNulls(size)
    }

    }
}