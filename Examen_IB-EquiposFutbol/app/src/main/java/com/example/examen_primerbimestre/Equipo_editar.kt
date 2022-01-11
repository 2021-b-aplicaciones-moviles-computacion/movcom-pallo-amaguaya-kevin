package com.example.examen_primerbimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Equipo_editar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipo_editar)

        var equipo = intent.getParcelableExtra<Equipo_class>("equipo_Editar")
        var idEquipo = intent.getIntExtra("posicion",0)

        Log.i("intent-recibido", "He recibido a: ${equipo}")
        if (equipo != null) {
            editarEquipo(equipo, idEquipo)
        }
    }

    fun editarEquipo(equipo: Equipo_class, id:Int){

        var textInput_nombre = findViewById<EditText>(R.id.textInput_Nombre)
        var textInput_idEquipo = findViewById<EditText>(R.id.textInput_Identificador)
        var textInput_deudas = findViewById<EditText>(R.id.textInput_Deudas)
        var textInput_fechaFundacion = findViewById<EditText>(R.id.textInput_FechaFundacion)
        var textInput_campeonatos = findViewById<EditText>(R.id.textInput_Campeonatos)
        var textView = findViewById<TextView>(R.id.textView_Name_equipo)

        var btn_editar_equipo = findViewById<Button>(R.id.button_Editar_equipo)
        textView.setText(equipo.idEquipo)

        textInput_idEquipo.setEnabled(false)
        textInput_fechaFundacion.setEnabled(false)

        btn_editar_equipo
            .setOnClickListener {
                BaseDeDatos.equipos[id].idEquipo = equipo.deudas
                BaseDeDatos.equipos[id].fechaFundacion = equipo.nombre
                BaseDeDatos.equipos[id].deudas = textInput_deudas.text.toString()
                BaseDeDatos.equipos[id].nombre = textInput_nombre.text.toString()
                BaseDeDatos.equipos[id].campeonatos = textInput_campeonatos.text.toString()
                setResult(
                    RESULT_OK
                )
                val intentRetorno = Intent(this,Equipo::class.java)
                startActivity(intentRetorno)
            }

        Log.i("editar-equipo", "Nombre: ${equipo.nombre} id: ${equipo.idEquipo} deudas: ${equipo.deudas}")

    }
}