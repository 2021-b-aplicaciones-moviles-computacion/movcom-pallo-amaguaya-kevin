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
import java.net.URI

class ProductosAdapter(
    private val contexto: ExplorarActivity,
    private val dataSet: ArrayList<BProductos>):
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
        Picasso.get()
                .load("${productos.enlace.toString()}")
                .into(viewHolder.iconoImagen)
        viewHolder.nombre.setOnClickListener {
            val intentExplicito = Intent(contexto, PreviewActivity::class.java)
            intentExplicito.putExtra("imagen", "${productos.enlace}")
            intentExplicito.putExtra("nombre", "${productos.nombre}")
            intentExplicito.putExtra("precio", "${productos.precio}")
            intentExplicito.putExtra("descripcion", "${productos.descripcion}")
            intentExplicito.putExtra("categoria", "${productos.categoria}")
            intentExplicito.putExtra("stock", "${productos.stock}")
            contexto.startActivity(intentExplicito)
        }
    }

    override fun getItemCount() = dataSet.size

}