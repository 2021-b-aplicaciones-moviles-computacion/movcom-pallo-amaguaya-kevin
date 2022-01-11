package com.example.examen_primerbimestre

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts

class Jugador : AppCompatActivity() {

    var idSeleccionado:Int = 0
    var jugadoresFiltrados = listOf<Jugadores_class>()

    var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
            result ->
        if (result.resultCode == Activity.RESULT_OK){
            val toast = Toast.makeText(this, "Jugador editado correctamente", Toast.LENGTH_LONG)
            toast.show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jugador)

        var idSeleccionado = intent.getIntExtra("idSeleccionado",0)
        var idEquipoAux = BaseDeDatos.equipos[idSeleccionado].idEquipo

        val listView = findViewById<ListView>(R.id.list_view_Jugador)
        val button_create = findViewById<Button>(R.id.button_crearJugador)

        jugadoresFiltrados = BaseDeDatos.jugadores.filter { it.idEquipo == idEquipoAux}


        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            jugadoresFiltrados
        )

        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(listView)

        button_create .setOnClickListener {
            //Botón para crear un nuevo Equipo
            val toast = Toast.makeText(this, "Boton sin funcionalidad", Toast.LENGTH_LONG)
            toast.show()
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_jugador, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        idSeleccionado = info.position //Obtengo el indice del menu
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_editar_jugador ->{
                //Log.i("menu-editar", "Id: ${idSeleccionado} info: ${BaseDeDatos.equipos[idSeleccionado]}")
                editarJugador(jugadoresFiltrados[idSeleccionado])
                return true
            }
            R.id.menu_eliminar_jugador ->{
                eliminarJugador(jugadoresFiltrados[idSeleccionado])
                return true

            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun editarJugador(jugador: Jugadores_class){
        val intentExplito = Intent(this, Jugador_editar::class.java)
        Log.i("intent-recibido","Voy a enviar: ${jugador} ")
        intentExplito.putExtra("jugador_Editar", jugador)
        resultLauncher.launch(intentExplito)
    }


    fun eliminarJugador(jugador: Jugadores_class){
        var indiceAux=-1
        BaseDeDatos.jugadores.forEachIndexed(){ indice:Int , jugadorAux : Jugadores_class->
            if(jugadorAux.nombreJugador == jugador.nombreJugador){
                indiceAux = indice
            }
        }
        Log.i("buscar_jugador", "Jugador encontrado en la posicion: ${indiceAux}")
        BaseDeDatos.jugadores.removeAt(indiceAux)
        var toast = Toast.makeText(this, "Jugador Eliminado", Toast.LENGTH_LONG)
        toast.show()
        var intent = Intent(this, Jugador::class.java)
        startActivity(intent)
    }


}