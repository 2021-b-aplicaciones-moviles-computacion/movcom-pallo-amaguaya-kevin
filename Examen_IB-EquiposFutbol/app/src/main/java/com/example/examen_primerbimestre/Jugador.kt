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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Jugador : AppCompatActivity() {

    val listJugador= ArrayList<Jugadores_class>()
    var idSeleccionado:Int = 0
    var equipoSeleccionado:String=""

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

        val listView = findViewById<ListView>(R.id.list_view_Jugador)
        val button_create = findViewById<Button>(R.id.button_crearJugador)


        equipoSeleccionado = intent.getStringExtra("id-equipo").toString()

        val db = Firebase.firestore
        db.collection("Equipos")
            .document("${equipoSeleccionado}")
            .collection("jugadores")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.i("datos_equipo", "${document.data.values}")
                    Log.i("datos_equipo", "${document.id}")
                    listJugador.add(
                        Jugadores_class(
                            document.id.toString(),
                            document.data.get("nombre").toString(),
                            document.data.get("sueldo").toString(),
                            document.data.get("fechaNacimiento").toString(),
                            document.data.get("dorsal").toString(),
                            document.data.get("lesiones").toString()
                        )
                    )
                }
                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    listJugador
                )
                listView.adapter = adaptador
                adaptador.notifyDataSetChanged()
                registerForContextMenu(listView)
            }
            .addOnFailureListener {
                Log.i("mostrar_jugador","No se muestra la lista de jugadores")
            }

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
                editarJugador(listJugador[idSeleccionado])
                return true
            }
            R.id.menu_eliminar_jugador ->{
                eliminarJugador(listJugador[idSeleccionado])
                return true

            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun editarJugador(jugador: Jugadores_class){
      val intentExplito = Intent(this, Jugador_editar::class.java)
        Log.i("intent-recibido","Voy a enviar: ${jugador} ")
        intentExplito.putExtra("jugador_Editar", jugador)
        intentExplito.putExtra("id_Equipo", equipoSeleccionado)
        resultLauncher.launch(intentExplito)
    }


    fun eliminarJugador(jugador: Jugadores_class){
        var db = Firebase.firestore
        db.collection("Equipos")
            .document("${equipoSeleccionado}")
            .collection("jugadores")
            .document("${jugador.idJugador}")
            .delete()
            .addOnSuccessListener {
                Log.i("eliminar_jugador","Jugador eliminado")
            }
            .addOnFailureListener {
                Log.i("eliminar_jugador","Jugador no eliminado")
            }
        var intent = Intent(this, Equipo::class.java)
        startActivity(intent)
    }


}