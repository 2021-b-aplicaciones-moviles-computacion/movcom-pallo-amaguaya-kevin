package com.example.simulationtinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Inicio : AppCompatActivity() {

    private var al: ArrayList<String> = arrayListOf()
    private var adaptador: ArrayAdapter<String?>? = null
    private var i = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        //Lista de Usuarios
        var listaUsuarios = arrayListOf<Usuario>()
        listaUsuarios.add(
            Usuario("Sendaya Maree","0985874512","Salesiana", 25)
        )
        listaUsuarios.add(
            Usuario("Gal Gadot","0985874512","PUCE", 22)
        )
        listaUsuarios.add(
            Usuario("Azucena Aymara","0978654515","Universidad Central", 42)
        )
        listaUsuarios.add(
            Usuario("Margarita Lugue","0985452452","UDLA", 22)
        )

        //RecyclerView
        val recyclerViewUsuarios = findViewById<RecyclerView>(R.id.recyclerView_Usuarios)

        inicializarRecyclerView(
            listaUsuarios,
            this,
            recyclerViewUsuarios
        )


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

    fun inicializarRecyclerView(
        lista:List<Usuario>,
        actividad: Inicio,
        recyclerView:RecyclerView
    ){
        val adaptador = RecyclerViewUsuario(
            actividad,
            lista,
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview. widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }

}
