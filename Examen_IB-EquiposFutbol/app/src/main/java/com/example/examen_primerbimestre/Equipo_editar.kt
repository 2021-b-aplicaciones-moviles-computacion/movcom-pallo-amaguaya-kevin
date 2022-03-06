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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Equipo_editar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipo_editar)

        var equipo = intent.getParcelableExtra<Equipo_class>("equipo_Editar")

        Log.i("intent-recibido", "He recibido a: ${equipo} ")
        if (equipo != null) {
            editarEquipo(equipo)
        }
    }

    fun editarEquipo(equipo: Equipo_class){
        var textInput_nombre = findViewById<EditText>(R.id.textInput_Nombre)
        var textInput_idEquipo = findViewById<EditText>(R.id.textInput_Identificador)
        var textInput_deudas = findViewById<EditText>(R.id.textInput_Deudas)
        var textInput_fechaFundacion = findViewById<EditText>(R.id.textInput_FechaFundacion)
        var textInput_campeonatos = findViewById<EditText>(R.id.textInput_Campeonatos)
        var textView = findViewById<TextView>(R.id.textView_Name_equipo)

        //Boton de editar equipo
        var btn_editar_equipo = findViewById<Button>(R.id.button_Editar_equipo)

        //Text view para mostrar el id
        textView.setText(equipo.id_Equipo)

        textInput_idEquipo.setEnabled(false)
        textInput_fechaFundacion.setEnabled(false)

        btn_editar_equipo
            .setOnClickListener {
                val db = Firebase.firestore

                val nuevosDatos = hashMapOf<String, Any>(
                    "nombre" to textInput_nombre.text.toString(),
                    "deudas" to textInput_deudas.text.toString(),
                    "campeonatos" to textInput_campeonatos.text.toString()
                )
                db.collection("Equipos")
                    .document("${equipo.id_Equipo}")
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
        Log.i("editar-equipo", "Nombre: ${equipo.nombre} id: ${equipo.id_Equipo} deudas: ${equipo.deudas}")
    }
}