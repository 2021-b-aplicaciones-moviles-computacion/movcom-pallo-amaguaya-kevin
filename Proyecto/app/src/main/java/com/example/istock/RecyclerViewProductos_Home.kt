package com.example.istock

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerViewProductos_Home(
    private val contexto: RecyclerViewCategorias,
    private val listProduc: List<Productos>,
    private val recyclerView: RecyclerView
    ): RecyclerView.Adapter<RecyclerViewProductos_Home.MyViewHolder>(){


    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val productName: TextView
        val valorProducto: TextView
        var imagenProd: ImageView

        init{
            productName = view.findViewById(R.id.textView_NameProd)
            valorProducto = view.findViewById(R.id.textView_PecioProd)
            imagenProd = view.findViewById(R.id.imageView6_Prod)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.ryclerview_horizontal_producto,
                parent,
                false,
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.productName.text = listProduc[position].nombre
        holder.valorProducto.text = listProduc[position].valorUnitario.toString()
        //Log.i("imagenProd","${listProduc[position].enlace} ${listProduc[position].cantidad} ${listProduc[position].valorUnitario}")
        Log.i("imagenProd","${listProduc[position].nombre} - ${listProduc[position].enlace}")
        Picasso.get()
            .load("${listProduc[position].enlace.toString()}")
            .into(holder.imagenProd)
    }

    override fun getItemCount(): Int {
        return listProduc.size
    }

}