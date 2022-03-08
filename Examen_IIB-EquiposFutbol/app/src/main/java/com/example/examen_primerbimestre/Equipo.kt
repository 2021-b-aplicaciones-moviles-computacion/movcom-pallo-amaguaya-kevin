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

class Equipo : AppCompatActivity() {

    val listEquipo= ArrayList<Equipo_class>()
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

        var db = Firebase.firestore //Obtengo la referencia
        db.collection("Equipos")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.i("datos_equipo","${document.data.values}")
                    Log.i("datos_equipo","${document.id}")
                    listEquipo.add( Equipo_class(document.id.toString(),
                        document.data.get("nombre").toString(),
                        document.data.get("campeonatos").toString(),
                        document.data.get("fechaFundacion").toString(),
                        document.data.get("deudas").toString()
                    )
                    )
                }
                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    listEquipo
                )
                listView.adapter = adaptador
                adaptador.notifyDataSetChanged()
                registerForContextMenu(listView)
            }
            .addOnFailureListener {
                Log.i("datos_equipo","No se recupero la coleccion")
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
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        idSeleccionado = info.position //Obtengo el indice del menu
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            //Menu - editar
            R.id.t_menu_editar ->{
                Log.i("menu-editar", "Id: ${idSeleccionado} info: ${listEquipo[idSeleccionado]}")
                val intentExplito = Intent(this, Equipo_editar::class.java)
                Log.i("intent-recibido","Voy a enviar: ${listEquipo[idSeleccionado]} ")
                intentExplito.putExtra("equipo_Editar", listEquipo[idSeleccionado])
                resultLauncher.launch(intentExplito)
                return true
            }
            //Menu - eliminar
            R.id.t_menu_eliminar ->{
                var db = Firebase.firestore
                db.collection("Equipos")
                    .document("${listEquipo[idSeleccionado].id_Equipo}")
                    .delete()
                    .addOnSuccessListener {
                        Log.i("eliminar_equipo","Equipo eliminado")
                    }
                    .addOnFailureListener {
                        Log.i("eliminar_equipo","Equipo no eliminado")
                    }
                var intent = Intent(this, Equipo::class.java)
                startActivity(intent)
                return true
            }
            //Menu - mostrar jugadores
            R.id.t_menu_verJugadores ->{
                var intentJugadores = Intent(this,Jugador::class.java)
                intentJugadores.putExtra("id-equipo",listEquipo[idSeleccionado].id_Equipo)
                resultLauncher.launch(intentJugadores)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}





