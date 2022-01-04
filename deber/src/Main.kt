import kotlin.collections.ArrayList

fun main(){
    var jugadoresArray: ArrayList<Jugador> = arrayListOf()
    var equiposArray: ArrayList<Equipo> = arrayListOf()
    var op: Int =0
    var iE: Int =1
    var iJ: Int =1
    while (op != 5) {
        println("\nMenú principal: Equipos de futbol - Jugador\n Selecciona una opción:")
        println("1. Agregar")
        println("2. Borrar")
        println("3. Modificar")
        println("4. Mostrar")
        println("5. Salir")
        op = readLine()?.toInt() as Int
        when(op){
            (1) ->{
                var opAgregar:Int=0
                while (opAgregar!=3){
                    println("\n Seleccione la opción que desea agregar")
                    println("1. Equipo")
                    println("2. Jugador")
                    println("3. Atras")
                    opAgregar = readLine()?.toInt() as Int
                    when (opAgregar){
                        (1)->{
                            equiposArray=agregarEquipo(iE,equiposArray)
                            iE++
                        }
                        (2)->{
                            jugadoresArray=agregarJugador(iJ,jugadoresArray,equiposArray)
                            iJ++
                        }
                    }
                }
                iJ=1
            }
            (2) ->{
                var opBorrar:Int=0
                while (opBorrar!=3){
                    println("\n Seleccione la opción que desea borrar")
                    println("1. Equipos")
                    println("2. Jugadores")
                    println("3. Atras")
                    opBorrar = readLine()?.toInt() as Int
                    when (opBorrar){
                        (1)->{
                            equiposArray=borrarEquipo(equiposArray,jugadoresArray)
                        }
                        (2)->{
                            jugadoresArray=borrarJugador(jugadoresArray)
                        }
                    }
                }
            }
            (3) ->{
                var opModificar:Int=0
                while (opModificar!=3){
                    println("\n Seleccione la opción que desea modificar")
                    println("1. Equipos")
                    println("2. Jugadores")
                    println("3. Atras")
                    opModificar = readLine()?.toInt() as Int
                    when (opModificar){
                        (1)->{
                            equiposArray=modificarEquipo(equiposArray)
                        }
                        (2)->{
                            jugadoresArray=modificarJugador(jugadoresArray)
                        }
                    }
                }
            }
            (4) ->{
                var opMostrar:Int=0
                while (opMostrar!=3){
                    println("\n Seleccione la opción que desea mostrar")
                    println("1. Equipos")
                    println("2. Jugadores")
                    println("3. Atras")
                    opMostrar = readLine()?.toInt() as Int
                    when (opMostrar){
                        (1)->{
                            mostrarEquipos(equiposArray)
                        }
                        (2)->{
                            mostrarJugadores(jugadoresArray)
                        }
                    }
                }
            }
            (5) ->{
                println("Saliendo...")
            }
        }
    }
}

/*********************** AGREGAR ***************************/
fun agregarEquipo(i: Int,equipos : ArrayList<Equipo>):ArrayList<Equipo>{
    println("Ingrese el nombre del Equipo")
    var auxNombre:String = readLine()?.toString() as String
    println("Ingrese la fecha de fundacion")
    var auxFechaFun:String = readLine().toString() as String
    println("Ingrese 'True' o 'False' si el equipo fue campeon")
    var auxCampeon:Boolean = readLine()?.toBoolean() as Boolean
    println("Ingrese el total de deuda del equipo")
    var auxDeuda:Float = readLine()?.toFloat() as Float
    var equipo = Equipo(i,auxNombre, auxCampeon, auxFechaFun,auxDeuda)
    equipos.add(equipo)
    return equipos
}

fun agregarJugador(i: Int,jugadores : ArrayList<Jugador>, equipos: ArrayList<Equipo> ):ArrayList<Jugador>{
    println("Ingrese el id del equipo")
    var idEquipoAux:Int = readLine()?.toInt() as Int
    var aux = 0
    equipos.forEach { equipo : Equipo ->
        if(equipo.obtenerIdEquipo() == idEquipoAux){
            println("Ingrese el nombre del Jugador")
            var nombreJugadorAux:String = readLine()?.toString() as String
            println("Ingrese el dorsal del Jugador")
            var dorsalAux:Int = readLine()?.toInt() as Int
            println("Ingrese la fecha de nacimiento")
            var fechaNaciAux:String = readLine().toString() as String
            println("Ingrese 'True' o 'False' si el jugador tuvo alguna lesion")
            var lesionAux:Boolean = readLine()?.toBoolean() as Boolean
            println("Ingrese el sueldo del jugador")
            var sueldoAux:Float = readLine()?.toFloat() as Float
            aux ++
            var jugador = Jugador(idEquipo = idEquipoAux,nombreJugador = nombreJugadorAux, fechaNacimiento = fechaNaciAux,
                sueldo = sueldoAux, lesion = lesionAux,dorsal = dorsalAux)
            jugadores.add(jugador)
        }
    }
    if(aux==0){
        println("******* El id del equipo ingresado no existe ********")
    }
    return jugadores
}

/*********************** BORRAR ***************************/
fun borrarJugador(jugadores : ArrayList<Jugador>):ArrayList<Jugador>{
    println("Ingrese el nombre del Jugador que desea borrar");
    var nombreAux: String = readLine()?.toString() as String
    var aux = 0
    println("Buscando.....")
    jugadores.forEach { jugador : Jugador ->
        if(jugador.obtenerNombrePlayer()==nombreAux){
            jugador.putIdEquipo(0)
            jugador.putNombrePlayer("")
            jugador.putDorsal(0)
            jugador.putLesion(false)
            jugador.putSueldo(0f)
            jugador.putFechaNacimiento("")
            aux++
            println("***** Jugador eliminado correctamente ******")
        }
    }
    if(aux==0){
        println("***** El jugador que ingresó no existe ******")
    }
    return jugadores
}

fun borrarEquipo( equipos : ArrayList<Equipo>,jugadores: ArrayList<Jugador> ):ArrayList<Equipo>{
    println("Ingrese el identificador del equipo que desea borrar");
    var idEquipoAux: Int = readLine()?.toInt() as Int
    var aux=0
    println("Buscando.....")
    jugadores.forEach { jugador : Jugador ->
        if(jugador.idEquipo==idEquipoAux){
            println("***** El equipo no se puede borrar ya que tiene jugadores pertenecientes al mismo ******")
        }
        else{
            equipos.forEach{ equipo : Equipo ->
                if(equipo.idEquipo==idEquipoAux){
                    equipo.putIdEquipo(0)
                    equipo.putNombre("")
                    equipo.putCampeon(false)
                    equipo.putDeudas(0f)
                    equipo.putfechaFundacion("")
                    println("***** Equipo eliminado correctamente ******")
                    aux++
                }
            }
        }
    }
    if(aux==0){
        println("***** El equipo que ingresó no existe ******")
    }
    return equipos
}

/*********************** MODIFICAR ***************************/
fun modificarJugador(jugadores : ArrayList<Jugador>):ArrayList<Jugador>{
    println("Ingrese el nombre del Jugador que desea modificar")
    val nombreAux: String = readLine()?.toString() as String
    var aux: Int = 0
    println("Buscando.....")
    jugadores.forEach { jugador : Jugador ->
        if(jugador.obtenerNombrePlayer()==nombreAux){
            println("Ingrese el id del nuevo equipo")
            jugador.putIdEquipo(readLine()?.toInt() as Int)
            println("Ingrese el nuevo dorsal")
            jugador.putDorsal(readLine()?.toInt() as Int)
            println("Ingrese el si el jugador tuvo una lesion")
            jugador.putLesion(readLine()?.toBoolean() as Boolean)
            println("Ingrese el nuevo sueldo del jugador")
            jugador.putSueldo(readLine()?.toFloat() as Float)
            println("***** Jugador modificado correctamente ******")
            aux++
        }
    }
    if(aux==0){
        println("******** El jugador ingresado no existe ******")
    }
    return jugadores
}

fun modificarEquipo( equipos : ArrayList<Equipo>):ArrayList<Equipo>{

    println("Ingrese el identificador del equipo que desea modificar");
    var idEquipoAux: Int = readLine()?.toInt() as Int
    var aux :Int = 0
    println("Buscando.....")
    equipos.forEach{ equipo : Equipo ->
        if(equipo.idEquipo==idEquipoAux){
            println("Ingrese el nuevo nombre del equipo");
            equipo.putNombre(readLine()?.toString() as String)
            println("Ingrese True or False si el equipo obtuvo un campeonato");
            equipo.putCampeon(readLine()?.toBoolean() as Boolean)
            println("Ingrese el nuevo valor de la deuda del equipo");
            equipo.putDeudas(readLine()?.toFloat() as Float)
            println("***** Equipo modificado correctamente ******")
            aux++
        }
    }
    if(aux==0) {
        println("***** El equipo ingresado no existe ******")
    }
 return equipos
}

/*********************** MOSTRAR ***************************/
fun mostrarEquipos( equipos : ArrayList<Equipo>){
    println(equipos.toString())
}

fun mostrarJugadores(jugadores : ArrayList<Jugador>){
    println(jugadores)
}

