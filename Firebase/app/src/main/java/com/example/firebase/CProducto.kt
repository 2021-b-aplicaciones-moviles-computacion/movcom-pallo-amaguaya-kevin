package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cproducto)

        val btn_crear = findViewById<Button>(R.id.button_crearProducto)
        btn_crear.setOnClickListener {
            crearProducto()
        }
    }

    fun crearProducto(){
        val editTextNombre = findViewById<EditText>(R.id.editText_nombre)
        val editTextPrecio = findViewById<EditText>(R.id.editText_precio)
        val nuevoProducto = hashMapOf<String, Any>(
            "nombre" to editTextNombre.text.toString(),
            "precio" to editTextPrecio.text.toString().toDouble()
        )

        val db= Firebase.firestore
        val referencia =  db.collection("producto")

        referencia.
                add(nuevoProducto)
            .addOnSuccessListener {
                editTextNombre.text.clear()
                editTextPrecio.text.clear()
            }
            .addOnFailureListener{

            }
    }
}