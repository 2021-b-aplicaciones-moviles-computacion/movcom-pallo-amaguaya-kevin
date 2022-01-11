package com.example.examen_primerbimestre

class BaseDeDatos {

    companion object{
        var equipos = arrayListOf<Equipo_class>()
        var jugadores = arrayListOf<Jugadores_class>()

        init {
            equipos.add(Equipo_class("1", "Deportivo Quito","5","02-02-1940","2000000.00"))
            equipos.add(Equipo_class("2", "Liga de Quito","15","02-02-1940","180000.00"))
            equipos.add(Equipo_class("3", "Barcelona","18","02-02-1940","258000.00"))

            jugadores.add(Jugadores_class("Kevin Pallo","2000.98", "20-11-1998","10","1", "true"))
            jugadores.add(Jugadores_class("Geovani Simbania","8000.98", "10-11-1998","8","2", "false"))
            jugadores.add(Jugadores_class("Jose Maria","22000.98", "20-09-1998","5","2", "true"))
            jugadores.add(Jugadores_class("Johao Aguirre","52000.98", "01-02-1999","8","3", "false"))
        }
    }
}