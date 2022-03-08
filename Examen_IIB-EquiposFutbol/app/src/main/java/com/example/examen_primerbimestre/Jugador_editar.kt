package com.example.examen_primerbimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Jugador_editar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jugador_editar)

        var jugador = intent.getParcelableExtra<Jugadores_class>("jugador_Editar")
        var idEquipo = intent.getStringExtra("id_Equipo").toString()

        Log.i("intent-recibido", "He recibido a: ${jugador}")
        if (jugador != null) {
           editarJugador(jugador, idEquipo)
        }
    }

   fun editarJugador(jugador: Jugadores_class, idEquipo:String){

        var textInput_nombreJugador = findViewById<EditText>(R.id.textInput_NombreJugador)
        var textInput_lesion = findViewById<EditText>(R.id.textInput_lesiones)
        var textInput_dorsal = findViewById<EditText>(R.id.textInput_Dorsal)
        var textInput_sueldo = findViewById<EditText>(R.id.textInput_Sueldo)
        var textView = findViewById<TextView>(R.id.textView_Name_Jugador)

        var btn_editar_equipo = findViewById<Button>(R.id.button_editar_jugador)
        textView.setText(jugador.idJugador)

        textInput_nombreJugador.setEnabled(false)

        btn_editar_equipo
            .setOnClickListener {
                val db = Firebase.firestore

                val nuevosDatos = hashMapOf<String, Any>(
                    "lesiones" to textInput_lesion.text.toString(),
                    "sueldo" to textInput_sueldo.text.toString(),
                    "dorsal" to textInput_dorsal.text.toString(),
                    "nombre" to jugador.nombreJugador.toString(),
                    "fechaNacimiento" to jugador.fechaNacimiento.toString()
                )

                db.collection("Equipos")
                    .document("${idEquipo}")
                    .collection("jugadores")
                    .document("${jugador.idJugador}")
                    .set(nuevosDatos)
                    .addOnSuccessListener {
                        Log.i("editar_equipo","Equipo editado")
                    }
                    .addOnFailureListener {
                        Log.i("editar_equipo","No editado")
                    }
                        setResult(
                            RESULT_OK
                        )
                        val intentRetorno = Intent(this,Equipo::class.java)
                        startActivity(intentRetorno)
                    }
                }

}



