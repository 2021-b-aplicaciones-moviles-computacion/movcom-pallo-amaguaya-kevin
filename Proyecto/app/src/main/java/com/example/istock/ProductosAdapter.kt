package com.example.istock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.istock.model.Productos
import java.net.URI

class ProductosAdapter(private val dataSet: ArrayList<Productos>):
    RecyclerView.Adapter<ProductosAdapter.ViewHolder>()  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView
        val precio: TextView
        val iconoImagen: ImageView
        init {
            nombre = view.findViewById(R.id.item_nombre_producto)
            precio = view.findViewById(R.id.item_precio_producto)
            iconoImagen = view.findViewById(R.id.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.productos_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val productos = dataSet[position]

        viewHolder.nombre.text = productos.nombre
        viewHolder.precio.text = productos.precio.toString()
        //viewHolder.iconoImagen.setImageURI(dataSet[position].toString())
    }

    override fun getItemCount() = dataSet.size

}