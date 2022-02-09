package com.example.simulationtinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Biografia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biografia)

        //BOTON IR PERFIL USUARIO
        var btn_irPerfil = findViewById<ImageButton>(R.id.imageButton_irPerfil)
        btn_irPerfil.setOnClickListener {
            startActivity(Intent(this, PerfilUsuario::class.java))
        }

        //BOTON IR EDITAR PERFIL
        var btn_editarPerfil = findViewById<ImageButton>(R.id.imageButton_editarPerfil)
        btn_editarPerfil.setOnClickListener {
            startActivity(Intent(this, EditarPerfil::class.java))
        }

    }
}