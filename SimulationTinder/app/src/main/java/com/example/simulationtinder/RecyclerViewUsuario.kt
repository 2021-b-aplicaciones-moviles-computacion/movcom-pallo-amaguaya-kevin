package com.example.simulationtinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewUsuario(
    private val contexto: Inicio,
    private val listaUsuarios: List<Usuario>,
    private val recyclerView: RecyclerView
    ):RecyclerView.Adapter<RecyclerViewUsuario.MyViewHolder>(){

        inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
            val nombre: TextView
            val edad: TextView
            val universidad: TextView
            val likes: TextView
            var numLikes = 0
            val btn_Like: ImageButton
            val btn_NoLike: ImageButton

            init{
                nombre = view.findViewById(R.id.textView_Nombre)
                edad = view.findViewById(R.id.textView_Edad)
                universidad = view.findViewById(R.id.textView_Universidad)
                btn_Like = view.findViewById(R.id.imageButton_Like)
                btn_Like.setOnClickListener {
                    this.anadirLike()
                }
                btn_NoLike = view.findViewById(R.id.imageButton_NoLike)
                btn_NoLike.setOnClickListener {
                    this.quitarLike()
                }
                likes= view.findViewById(R.id.textView_Likes)
            }

            fun anadirLike(){
                this.numLikes++
                likes.text = this.numLikes.toString()
            }

            fun quitarLike(){
                this.numLikes--
                likes.text = this.numLikes.toString()
            }

        }

        //Setear el layout que vamos a utilizar
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.personas_cercanas,
                    parent,
                    false,
                )
            return MyViewHolder(itemView)
        }

        //Setear los datos de cada iteración del arreglo
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val usuario = listaUsuarios[position]
            holder.nombre.text = usuario.nombre
            holder.edad.text = usuario.edad.toString()
            holder.universidad.text = usuario.universidad
            holder.likes.text = "0"
        }

        //tamanio del arreglo
        override fun getItemCount(): Int {
            return listaUsuarios.size
        }
}