package com.example.istock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var btn_Ir_registrar = findViewById<Button>(R.id.button_CompararAhora)

        btn_Ir_registrar.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }
}