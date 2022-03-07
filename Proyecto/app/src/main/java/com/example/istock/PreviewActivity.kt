package com.example.istock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        Picasso.get()
            .load(imagen)
            .into(imageView3)
        textView19.setText(nombre)
        textView27.setText("$  $precio")
        textView28.setText(categoria)
        textView29.setText(descripcion)

    }
}