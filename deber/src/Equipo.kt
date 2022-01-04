open class Equipo {

    public var nombre:String= ""
    public var idEquipo: Int=0
    public var campeon:Boolean = false
    public var fechaFundacion: String =""
    public var deudas: Float = 0f

    constructor(
        idEquipo: Int,
        nombre:String,
        campeon:Boolean,
        fechaFundacion: String,
        deudas: Float
    ){
        this.idEquipo = idEquipo
        this.nombre = nombre
        this.campeon = campeon
        this.fechaFundacion = fechaFundacion
        this.deudas =deudas

        }

    fun obtenerNombre():String{
        return this.nombre
    }
    fun obtenerfechaFundacion():String{
        return this.fechaFundacion
    }
    fun obtenerCampeon():Boolean{
        return this.campeon
    }
    fun obtenerIdEquipo():Int{
        return this.idEquipo
    }
    fun obtenerDeudas():Float{
        return this.deudas
    }

    fun putNombre(nombre:String):Unit{
        this.nombre = nombre
    }
    fun putfechaFundacion(fecha:String):Unit{
        this.fechaFundacion = fecha
    }
    fun putCampeon(campeon: Boolean):Unit{
        this.campeon = campeon
    }
    fun putIdEquipo(jugadores:Int):Unit{
        this.idEquipo = jugadores
    }
    fun putDeudas(deudas: Float):Unit{
        this.deudas = deudas
    }

    override fun toString(): String {
        return "Equipo id Equipo: $idEquipo, Nombre: '$nombre',  Campeon: $campeon, Fecha fundacion: '$fechaFundacion', Deuda: $deudas\n"
    }


}



