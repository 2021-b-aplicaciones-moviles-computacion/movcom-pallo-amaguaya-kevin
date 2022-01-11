package com.example.examen_primerbimestre

import android.os.Parcel
import android.os.Parcelable

class Jugadores_class(
      var nombreJugador:String?,
      var sueldo: String?,
      var fechaNacimiento: String?,
      var dorsal: String?,
      var idEquipo: String?,
      var lesion: String?,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return """
                |Nombre: ${nombreJugador} 
                |Equipo: ${idEquipo} 
                |Fecha de nacimiento: ${fechaNacimiento} 
                |Dorsal: ${dorsal}  
                |Lesion: ${lesion} 
                |Sueldo: ${sueldo}
               """.trimMargin()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(nombreJugador)
        dest?.writeString(fechaNacimiento)
        dest?.writeString(sueldo)
        dest?.writeString(lesion)
        dest?.writeString(dorsal)
        dest?.writeString(idEquipo)
    }

    companion object CREATOR : Parcelable.Creator<Jugadores_class> {
        override fun createFromParcel(parcel: Parcel): Jugadores_class {
            return Jugadores_class(parcel)
        }

        override fun newArray(size: Int): Array<Jugadores_class?> {
            return arrayOfNulls(size)
        }
    }

}
