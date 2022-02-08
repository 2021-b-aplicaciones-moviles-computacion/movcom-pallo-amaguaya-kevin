package com.example.simulationtinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Matchs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matchs)

        val btn_Irperfil = findViewById<Button>(R.id.button_perfil2)
        btn_Irperfil.setOnClickListener {
            startActivity(Intent(this, PerfilUsuario::class.java))
        }

        val btn_IrInicio = findViewById<Button>(R.id.btn_tinder2)
        btn_IrInicio.setOnClickListener {
            startActivity(Intent(this, Inicio::class.java))
        }

    }


}