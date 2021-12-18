import java.io.File
import java.util.*

class Jugador{

    public var nombreJugador:String =""
    public var sueldo: Float = 0f
    public var fechaNacimiento: String = ""
    public var dorsal: Int = 0
    public var idEquipo: Int = 0
    public var lesion: Boolean = false

    constructor(
        nombreJugador:String,
        sueldo: Float,
        fechaNacimiento:String,
        dorsal: Int,
        lesion: Boolean,
        idEquipo: Int
    ){
        this.idEquipo = idEquipo
        this.sueldo = sueldo
        this.dorsal = dorsal
        this.lesion = lesion
        this.nombreJugador = nombreJugador
        this.fechaNacimiento = fechaNacimiento
    }

    fun obtenerNombrePlayer():String{
        return this.nombreJugador
    }
    fun obtenerfechaNacimiento():String{
        return this.fechaNacimiento
    }
    fun obtenerLesion():Boolean{
        return this.lesion
    }
    fun obtenerSueldo():Float{
        return this.sueldo
    }
    fun obtenerDorsal():Int{
        return this.dorsal
    }
    fun obtenerIdEquipo():Int{
        return this.idEquipo
    }

    fun putNombrePlayer(nombre:String):Unit{
        this.nombreJugador = nombre
    }
    fun putLesion(lesion: Boolean):Unit{
        this.lesion = lesion
    }
    fun putDorsal(dorsal:Int):Unit{
        this.dorsal = dorsal
    }
    fun putSueldo(sueldo: Float):Unit{
        this.sueldo = sueldo
    }
    fun putIdEquipo(idEquipo: Int):Unit{
        this.idEquipo = idEquipo
    }
    fun putFechaNacimiento(fechaNacimiento: String):Unit{
        this.fechaNacimiento = fechaNacimiento
    }

    override fun toString(): String {
        return "Jugador  Nombre: '$nombreJugador', Sueldo: $sueldo, Fecha nacimiento: '$fechaNacimiento', Dorsal: $dorsal, id Equipo: $idEquipo, Lesiones: $lesion\n"
    }

}