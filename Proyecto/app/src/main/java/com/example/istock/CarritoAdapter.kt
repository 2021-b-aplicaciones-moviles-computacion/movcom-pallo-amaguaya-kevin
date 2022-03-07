package com.example.istock

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.istock.model.BProductos
import com.squareup.picasso.Picasso

class CarritoAdapter (
    private val dataSet: ArrayList<BProductos>):
    RecyclerView.Adapter<CarritoAdapter.ViewHolder>()  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView
        val precio: TextView
        val iconoImagen: ImageView
        init {
            nombre = view.findViewById(R.id.textView_nombreProducto_carrito)
            precio = view.findViewById(R.id.textView_precio_Carrito)
            iconoImagen = view.findViewById(R.id.imageView7)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.carrito_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val productos = dataSet[position]
        viewHolder.nombre.text = productos.nombre
        viewHolder.precio.text = productos.precio.toString()
        Picasso.get()
            .load("${productos.enlace.toString()}")
            .resize(300,300)
            .centerCrop()
            .into(viewHolder.iconoImagen)
        }

    override fun getItemCount() = dataSet.size
}