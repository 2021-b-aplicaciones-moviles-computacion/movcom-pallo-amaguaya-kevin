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

class Equipo : AppCompatActivity() {

    var idSeleccionado:Int = 0

    var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result ->
        if (result.resultCode == Activity.RESULT_OK){
            val toast = Toast.makeText(this, "Equipo editado correctamente", Toast.LENGTH_LONG)
            toast.show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipo)

        val listView = findViewById<ListView>(R.id.list_view_Jugador)
        val button_create = findViewById<Button>(R.id.button_crear_equipo)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BaseDeDatos.equipos
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
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        idSeleccionado = info.position //Obtengo el indice del menu
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.t_menu_editar ->{
                //Log.i("menu-editar", "Id: ${idSeleccionado} info: ${BaseDeDatos.equipos[idSeleccionado]}")
                editarEquipo(BaseDeDatos.equipos[idSeleccionado])
                return true
            }
            R.id.t_menu_eliminar ->{
                eliminarEquipo()
                return true

            }
            R.id.t_menu_verJugadores ->{
                verJugadoresEquipo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun editarEquipo(equipo: Equipo_class){
        val intentExplito = Intent(this, Equipo_editar::class.java)
        Log.i("intent-recibido","Voy a enviar: ${equipo} ")
        intentExplito.putExtra("equipo_Editar", equipo)
        intentExplito.putExtra("nombre_jugador", idSeleccionado)
        resultLauncher.launch(intentExplito)
    }


    fun eliminarEquipo(){
        BaseDeDatos.equipos.removeAt(idSeleccionado)
        var toast = Toast.makeText(this, "Equipo Eliminado", Toast.LENGTH_LONG)
        toast.show()
        var intent = Intent(this, Equipo::class.java)
        startActivity(intent)

        /*
        var cont = 0
        var idEquipoAux = BaseDeDatos.equipos[idSeleccionado].idEquipo
        BaseDeDatos.jugadores.forEach{ jugador : Jugadores_class ->
            if(jugador.idEquipo == idEquipoAux){
                cont++
            }
            else{
                BaseDeDatos.equipos.removeAt(idSeleccionado)
                var toast = Toast.makeText(this, "Equipo Eliminado", Toast.LENGTH_LONG)
                toast.show()
                var intent = Intent(this, Equipo::class.java)
                startActivity(intent)
            }
        }
        if(cont != 0){
            var toast = Toast.makeText(this, "No se puede eliminar el equipo ya que contiene jugadores", Toast.LENGTH_LONG)
            toast.show()
        }
        */
    }
    fun verJugadoresEquipo(){
        var intentJugadores = Intent(this,Jugador::class.java)
        intentJugadores.putExtra("idSeleccionado",idSeleccionado)
        resultLauncher.launch(intentJugadores)
    }

    }



