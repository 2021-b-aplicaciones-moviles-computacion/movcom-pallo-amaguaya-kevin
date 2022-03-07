package com.example.istock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.istock.model.BProductos
import com.example.istock.utils.Utils.mCarrito
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_preview.*

class PreviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        val bundle = intent.extras
        val imagen = bundle?.getString("imagen").toString()
        val nombre = bundle?.getString("nombre").toString()
        val precio = bundle?.getString("precio").toString()
        val descripcion = bundle?.getString("descripcion").toString()
        val categoria = bundle?.getString("categoria").toString()
        val stock = bundle?.getString("stock").toString()
        Picasso.get()
            .load(imagen)
            .into(imageView3)
        textView19.setText(nombre)
        textView27.setText("$  $precio")
        textView28.setText(categoria)
        textView29.setText(descripcion)
        imageButton.setOnClickListener {
            val i = Intent(this, ExplorarActivity::class.java)
            startActivity(i)
        }
        btnAñadirCarrito.setOnClickListener {
            mCarrito.add(
                BProductos(nombre, descripcion, stock.toDouble(), categoria, precio.toDouble(), imagen )
            )
            val i = Intent(this, CarritoActivity::class.java)
            startActivity(i)
        }
    }
}