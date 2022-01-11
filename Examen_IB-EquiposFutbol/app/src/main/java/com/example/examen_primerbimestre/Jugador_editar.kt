package com.example.examen_primerbimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Jugador_editar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jugador_editar)

        var jugador = intent.getParcelableExtra<Jugadores_class>("jugador_Editar")

        Log.i("intent-recibido", "He recibido a: ${jugador}")

        if (jugador != null) {
            editarJugador(jugador )
        }

    }

    fun editarJugador(jugador: Jugadores_class){
        Log.i("editar-jugador", "Nombre: ${jugador.nombreJugador} id: ${jugador.idEquipo} lesiones: ${jugador.lesion}" +
                "sueldo: ${jugador.sueldo} fechaNaci: ${jugador.fechaNacimiento} dorsal: ${jugador.dorsal}")


        var textInput_nombreJugador = findViewById<EditText>(R.id.textInput_NombreJugador)
        var textInput_idEquipo = findViewById<EditText>(R.id.textInput_idEquipoJugador)
        var textInput_lesion = findViewById<EditText>(R.id.textInput_lesiones)
        var textInput_dorsal = findViewById<EditText>(R.id.textInput_Dorsal)
        var textInput_sueldo = findViewById<EditText>(R.id.textInput_Sueldo)
        var textView = findViewById<TextView>(R.id.textView_Name_Jugador)

        var btn_editar_equipo = findViewById<Button>(R.id.button_editar_jugador)
        textView.setText(jugador.nombreJugador)

        textInput_nombreJugador.setEnabled(false)

        btn_editar_equipo
            .setOnClickListener {
                BaseDeDatos.jugadores.forEach{ jugadorAux : Jugadores_class ->
                    if(jugadorAux.nombreJugador == jugador.nombreJugador){
                        jugadorAux.fechaNacimiento = jugador.sueldo
                        jugadorAux.dorsal = textInput_dorsal.text.toString()
                        jugadorAux.sueldo = textInput_sueldo.text.toString()
                        jugadorAux.lesion = textInput_lesion.text.toString()
                        jugadorAux.idEquipo = textInput_idEquipo.text.toString()
                        setResult(
                            RESULT_OK
                        )
                        val intentRetorno = Intent(this,Jugador::class.java)
                        startActivity(intentRetorno)
                    }
                }
            }
    }

}



