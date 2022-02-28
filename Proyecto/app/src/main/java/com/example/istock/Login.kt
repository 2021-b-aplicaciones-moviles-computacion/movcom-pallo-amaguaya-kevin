package com.example.istock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var btn_Ir_ingresar = findViewById<Button>(R.id.button_ingresar)

        btn_Ir_ingresar.setOnClickListener {
            startActivity(Intent(this, Productos::class.java))
        }

    }
}