package com.example.simulationtinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView

class EditarPerfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        //DA1E1E Color de la flecha

        //BOTON REGRESAR BIOGRAFIA
        val btn_backBiografia = findViewById<ImageButton>(R.id.imageButton_BackBibliografia)
        btn_backBiografia.setOnClickListener {
            startActivity(Intent(this, Biografia::class.java))
        }



    }


}