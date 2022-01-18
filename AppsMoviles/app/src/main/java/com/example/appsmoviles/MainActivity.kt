package com.example.appsmoviles

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    val CODIGO_RESPUESTA_INTENT_IMPLICITO = 402

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


        /*--------------------METODOS PARA LA BASE DE DATOS USUARIOS--------------*/
        EBaseDeDatos.TablaUsuario = ESqliteHelperUsuario(this)
        if (EBaseDeDatos.TablaUsuario != null) {
            val idQuemado = 2
            EBaseDeDatos.TablaUsuario?.crearUsuarioFormulario(
                "Kevin",
                "Kevin desc"
            )
            var consulta = EBaseDeDatos.TablaUsuario?.consultarUsuarioFormulario(
                idQuemado
            )
            Log.i("bdd", "Primera Consulta: ${consulta?.nombre}")
            EBaseDeDatos.TablaUsuario?.actualizarUsuarioFormulario(
                idQuemado,
                "Mauricio",
                "Muricio desc"
            )
            consulta = EBaseDeDatos.TablaUsuario?.consultarUsuarioFormulario(
                idQuemado
            )
            Log.i("bdd", "Primera Consulta: ${consulta?.nombre}")
            EBaseDeDatos.TablaUsuario?.eliminarUsuarioFormulario(
                idQuemado
            )
            consulta = EBaseDeDatos.TablaUsuario?.consultarUsuarioFormulario(
                idQuemado
            )
            Log.i("bdd", "Primera Consulta: ${consulta?.nombre}")
        }
        /*----------------------------------*/

        val buttonContinuar=findViewById<Button>(R.id.button_Continuar)
        buttonContinuar.setOnClickListener{
            irActividad(cicloDeVida::class.java)
        }
        val buttonLsView=findViewById<Button>(R.id.btn_ir_list_view)
        buttonLsView.setOnClickListener{
            irActividad(BListView::class.java)
        }

        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicto)
        botonIntentImplicito.
                setOnClickListener {
                    val intentConRespuesta = Intent(
                        Intent.ACTION_PICK,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                    )
                    startActivityForResult(intentConRespuesta, CODIGO_RESPUESTA_INTENT_IMPLICITO)
                }

        //Intent Expllicito

        val buttonIntent=findViewById<Button>(R.id.btn_intent)
        buttonIntent.setOnClickListener{
            abrirActividadConParametros(CIntentExplicitoParametros::class.java)
        }

        val buttonRecyclerView = findViewById<Button>(R.id.btn_ir_recycler_view)

        buttonRecyclerView
            .setOnClickListener {
                abrirActividadConParametros(GRecyclerView::class.java)
            }
    }

    fun abrirActividadConParametros(clase: Class<*>){
        val intentExplicito = Intent(this, clase)
        //Enviar parametros (solamente variables primitivas)
        intentExplicito.putExtra("nombre","Kevin")
        intentExplicito.putExtra("apellido","Pallo")
        intentExplicito.putExtra("edad",23)

        intentExplicito.putExtra("a", BEntrenador("Joaquín", "Correa@epn.ec"))

        resultLauncher.launch(intentExplicito)

        //startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO) //401


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            CODIGO_RESPUESTA_INTENT_EXPLICITO   ->{
                if(resultCode == RESULT_OK){
                    Log.i("intent-epn","${data?.getStringExtra("nombreModificado")}")
                }
                if(resultCode == RESULT_CANCELED){
                    Log.i("intent-epn","Cancelado")
                }
            }
            CODIGO_RESPUESTA_INTENT_IMPLICITO -> {
                if(resultCode == RESULT_OK){
                    if(data!=null){
                        if(data.data != null){
                            val uri: Uri =data.data!!
                            val cursor = contentResolver.query(
                                uri,
                                null,
                                null,
                                null,
                                null,
                                null
                            )
                            cursor?.moveToFirst()
                            val indiceTelefono = cursor ?.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                            )
                            val telefono = cursor?.getString(
                                indiceTelefono!!
                            )
                            cursor?.close()
                            Log.i("intent-epn", "Telefono: ${telefono}")
                        }
                    }
                }

            }
        }
        //resultLauncher.launch(intentExplicito)
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