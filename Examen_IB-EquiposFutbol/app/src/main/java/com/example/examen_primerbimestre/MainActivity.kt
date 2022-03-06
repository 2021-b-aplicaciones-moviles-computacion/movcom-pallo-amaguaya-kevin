package com.example.examen_primerbimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Boton continuar para pasar a la interface de equipos
        val buttonContinue = findViewById<Button>(R.id.button_continue)
        buttonContinue.setOnClickListener {
            val intent = Intent(this, Equipo::class.java)
            startActivity(intent)
        }
    }


}