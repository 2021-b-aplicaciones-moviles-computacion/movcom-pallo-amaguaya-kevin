package com.example.appsmoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRecyclerView : AppCompatActivity() {

    var totalLikes:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view)

        val listaEntrenador = arrayListOf<BEntrenador>()
        listaEntrenador
            .add(
                BEntrenador(
                    "Kevin",
                    "1718137159"
                )
            )
        listaEntrenador
            .add(
                BEntrenador(
                    "Mauricio",
                    "0198137123"
                )
            )

        val recyclerViewEntrenador = findViewById<RecyclerView>(R.id.recyclerView)
        inicializarRecyclerView(
            listaEntrenador,
            this,
            recyclerViewEntrenador
        )
    }
    
    fun inicializarRecyclerView(
        lista:List<BEntrenador>,
        actividad: GRecyclerView,
        recyclerView:RecyclerView
    ){
        val adaptador = FRecyclerViewAdaptadorNombreCedula(
            actividad,
            lista,
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }

    fun aumentarTotalLikes(){
        totalLikes = totalLikes + 1
        val textView = findViewById<TextView>(R.id.tv_likes)
        textView.text = totalLikes.toString()
    }



}