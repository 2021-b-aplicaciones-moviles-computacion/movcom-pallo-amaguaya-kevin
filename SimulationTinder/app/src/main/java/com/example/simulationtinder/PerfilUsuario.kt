package com.example.simulationtinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class PerfilUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_usuario)

        val btn_backInicio = findViewById<ImageButton>(R.id.btn_backInicio)
        btn_backInicio.setOnClickListener {
            startActivity(Intent(this, Inicio::class.java))
        }

        var btn_perfilUsuario = findViewById<ImageButton>(R.id.imageButton_perfil)
        btn_perfilUsuario.setOnClickListener {
            startActivity(Intent(this, Biografia::class.java))
        }

        var btn_editarPerfil = findViewById<ImageButton>(R.id.imageButton_editarPerfil2)
        btn_editarPerfil.setOnClickListener {
            startActivity(Intent(this, EditarPerfil::class.java))
        }
    }
}