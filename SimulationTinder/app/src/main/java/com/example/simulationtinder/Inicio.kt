package com.example.simulationtinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import com.lorentzos.flingswipe.SwipeFlingAdapterView

class Inicio : AppCompatActivity() {

    private var al: ArrayList<String> = arrayListOf()
    private var adaptador: ArrayAdapter<String?>? = null
    private var i = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        al.add("php")
        al.add("c")
        al.add("python")
        al.add("java")
        al.add("html")
        al.add("c++")
        al.add("css")
        al.add("javascript")

        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, al as List<String?>)

        var flingContainer: SwipeFlingAdapterView = findViewById(R.id.frame)

        flingContainer!!.adapter = adaptador
        flingContainer!!.setFlingListener(object : SwipeFlingAdapterView.onFlingListener {
            override fun removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!")
                al!!.removeAt(0)
                adaptador!!.notifyDataSetChanged()
            }

            override fun onLeftCardExit(dataObject: Any) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Log.i("Izquierda","Izquierda")
                //Toast.makeText(this, "Izquierda", Toast.LENGTH_LONG).show()
            }

            override fun onRightCardExit(dataObject: Any) {
                Log.i("Derecha","Derecha")
                //Toast.makeText(this, "Derecha", Toast.LENGTH_LONG).show()

            }

            override fun onAdapterAboutToEmpty(itemsInAdapter: Int) {
                // Ask for more data here
                al!!.add("XML $i")
                adaptador!!.notifyDataSetChanged()
                Log.d("LIST", "notified")
                i++
            }

            override fun onScroll(scrollProgressPercent: Float) {

            }
        })


        // Optionally add an OnItemClickListener
        flingContainer!!.setOnItemClickListener { itemPosition, dataObject ->
            Toast.makeText(this,"Presionaste la pantalla!", Toast.LENGTH_LONG).show()
        }

    //BOTON PERFIl
        val btn_perfil = findViewById<Button>(R.id.button_perfil)
        btn_perfil.setOnClickListener {
            startActivity(Intent(this, PerfilUsuario::class.java))
        }

    //BOTON MATCH
        val btn_match = findViewById<Button>(R.id.button_IrMatch)
        btn_match.setOnClickListener {
            startActivity(Intent(this, Matchs::class.java))
        }

    }




}
