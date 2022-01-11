package com.example.appsmoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.OnClickAction
import android.util.Log
import android.widget.Button

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)

        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad",0)

        val entrenador = intent.getParcelableExtra<BEntrenador>("a")

        Log.i("intent", "Value ${nombre} ${apellido} ${edad} ${entrenador}")

        val boton = findViewById<Button>(R.id.bt_D_respuesta)
        boton.setOnClickListener{
            devolverRespuesta()
        }
    }
    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nombreModificado", "Mauricio")
        intentDevolverParametros.putExtra("edadModificado", 20)
        intentDevolverParametros.putExtra("entrenador", BEntrenador("Maria", "Chunchun"))
        //setRsult de la clase padre
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        //Finish de la clase padre
        finish()
    }
}