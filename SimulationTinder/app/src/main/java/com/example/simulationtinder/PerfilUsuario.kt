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
        
    }
}