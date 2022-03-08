package com.example.istock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.istock.model.BProductos
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_carrito.*

class CarritoAdapter(
    private val dataSet: ArrayList<BProductos>,
    suma: Double
):
    RecyclerView.Adapter<CarritoAdapter.ViewHolder>()  {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView
        val precio: TextView
        val iconoImagen: ImageView
        val iconoEliminar:  ImageView
        val iconoAumentar:  ImageView
        val iconoQuitar:  ImageView
        val cantidad: TextView
        init {
            nombre = view.findViewById(R.id.textView_nombreProducto_carrito)
            precio = view.findViewById(R.id.textView_precio_Carrito)
            iconoImagen = view.findViewById(R.id.imageView7)
            iconoEliminar = view.findViewById(R.id.imageView9)
            iconoAumentar = view.findViewById(R.id.imageView8)
            iconoQuitar = view.findViewById(R.id.imageView10)
            cantidad = view.findViewById(R.id.textView40)
        }
        fun bind(index: Int, precioAnterior: Double?){
            iconoEliminar.setOnClickListener{deleteItem(index)}
            iconoAumentar.setOnClickListener {
                if (cantidad.text.toString().toDouble() >= 1){
                    val newCantidad = cantidad.text.toString().toDouble() + 1
                    val newPrecio = precio.text.toString().toDouble() + precioAnterior!!
                    cantidad.setText(newCantidad.toString())
                    precio.setText((Math.round(newPrecio * 1000.0)/1000.0).toString())
                }
            }
            iconoQuitar.setOnClickListener {
                if (cantidad.text.toString().toDouble() > 1){
                    val newCantidad = cantidad.text.toString().toInt() - 1
                    val newPrecio = precio.text.toString().toDouble() - precioAnterior!!
                    cantidad.setText(newCantidad.toString())
                    precio.setText((Math.round(newPrecio * 1000.0)/1000.0).toString())
                }
            }

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
            .load(productos.enlace.toString())
            .resize(300,300)
            .onlyScaleDown()
            .into(viewHolder.iconoImagen)
        viewHolder.bind( position, productos.precio)
        }

    override fun getItemCount() = dataSet.size

    fun deleteItem(index: Int){
        dataSet.removeAt(index)
        notifyDataSetChanged()
    }
}