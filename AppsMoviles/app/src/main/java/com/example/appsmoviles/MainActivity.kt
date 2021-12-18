package com.example.appsmoviles

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
        ){
            result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    val data = result.data
                    Log.i("intent", "${data?.getStringExtra("nombreModificado")}")
                    Log.i("intent", "${data?.getIntExtra("edadModificado",0)}")
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonContinuar=findViewById<Button>(R.id.button_Continuar)
        buttonContinuar.setOnClickListener{
            irActividad(cicloDeVida::class.java)
        }
        val buttonLsView=findViewById<Button>(R.id.btn_ir_list_view)
        buttonLsView.setOnClickListener{
            irActividad(BListView::class.java)
        }
        val buttonIntent=findViewById<Button>(R.id.btn_intent)
        buttonIntent.setOnClickListener{
            abrirActividadConParametros(CIntentExplicitoParametros::class.java)
        }

    }

    fun abrirActividadConParametros(clase: Class<*>){
        val intentExplicito = Intent(this, clase)
        //Enviar parametros (solamente variables primitivas)
        intentExplicito.putExtra("nombre","Kevin")
        intentExplicito.putExtra("apellido","Pallo")
        intentExplicito.putExtra("edad",23)
        //startActivityForResult(intent, CODIGO_RESPUESTA_INTENT_EXPLICITO)
        resultLauncher.launch(intentExplicito)
    }

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}