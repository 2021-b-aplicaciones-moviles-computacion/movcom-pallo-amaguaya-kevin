package com.example.appsmoviles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorNombreCedula(
    private val contexto: GRecyclerView,
    private val listaEntrenador: List<BEntrenador>,
    private val recyclerView: RecyclerView
) :RecyclerView.Adapter<FRecyclerViewAdaptadorNombreCedula.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val nombreTextView : TextView
        val cedulaTextView : TextView
        val likeTextView: TextView
        val accionButton: Button
        val numeroLikes = 0

        init{
            nombreTextView = view.findViewById(R.id.tv_nombre)
            cedulaTextView = view.findViewById(R.id.tv_cedula)
            likeTextView = view.findViewById(R.id.tv_likes)
            accionButton = view.findViewById(R.id.btn_dar_like)
            accionButton.setOnClickListener {
                this.anadirLike()
            }

        }
        fun anadirLike(){

        }
    }

    //Setear el layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_vista,
                parent,
                false,
            )
        return MyViewHolder(itemView)
    }

        //Setear los datos de cada iteración del arreglo
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenador = listaEntrenador[position]
        holder.nombreTextView.text = entrenador.nombre
        holder.cedulaTextView.text = entrenador.descripcion
        holder.accionButton.text = "Like ${entrenador.nombre}"
        holder.likeTextView.text = "0"
    }

    //tamanio del arreglo
    override fun getItemCount(): Int {
        return listaEntrenador.size
    }
}