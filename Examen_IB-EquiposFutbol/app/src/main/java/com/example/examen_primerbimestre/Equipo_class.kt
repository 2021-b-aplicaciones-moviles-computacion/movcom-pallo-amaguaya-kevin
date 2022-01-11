package com.example.examen_primerbimestre

import android.os.Parcel
import android.os.Parcelable

class Equipo_class(
    var idEquipo: String?,
    var nombre:String?,
    var campeonatos: String?,
    var fechaFundacion: String?,
    var deudas: String?
): Parcelable {

    override fun toString(): String {
        return """
                   |Nombre: ${nombre} 
                   |Identificador: ${idEquipo} 
                   |Fecha de fundacion: ${fechaFundacion} 
                   |Campeonatos: ${campeonatos} 
                   |Deudas: ${deudas}  
               """.trimMargin()
    }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0;
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(nombre)
        dest?.writeString(fechaFundacion)
        dest?.writeString(campeonatos)
        dest?.writeString(deudas)
        dest?.writeString(idEquipo)
    }

    companion object CREATOR : Parcelable.Creator<Equipo_class> {
        override fun createFromParcel(parcel: Parcel): Equipo_class {
            return Equipo_class(parcel)
        }

        override fun newArray(size: Int): Array<Equipo_class?> {
            return arrayOfNulls(size)
        }
    }
}