package com.example.istock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecyclerViewCategorias(
    private val contexto: HomeActivity,
    private val listaCategoria: List<Categorias>,
    private val recyclerView: RecyclerView
):RecyclerView.Adapter<RecyclerViewCategorias.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val categoriaName: TextView
        val productName: TextView
        val valorProducto: TextView

        init{
            categoriaName = view.findViewById(R.id.textView_Productos)
            productName = view.findViewById(R.id.textView_nombreProducto)
            valorProducto = view.findViewById(R.id.textView_valorUnitario)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.productos_recycler_view,
                parent,
                false,
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val categoria = listaCategoria[position]
        holder.categoriaName.text = categoria.nombre
        holder.productName.text = "Magos"
        holder.valorProducto.text = "0.50"

       /* var db = Firebase.firestore
        db.collection("Categoria")
            .document("${categoria.id_Categorias}")
            .collection("${categoria.nombre}")
            .get()
            .addOnSuccessListener { resultado ->
                for(col in resultado){
                    holder.productName.text = col.data.get("nombre").toString()
                    holder.valorProducto.text = col.data.get("valorUnitario").toString()
                }
                //holder.productName.text = n
                //holder.valorProducto.text = vP
            }*/
    }

    override fun getItemCount(): Int {
        return listaCategoria.size
    }

}