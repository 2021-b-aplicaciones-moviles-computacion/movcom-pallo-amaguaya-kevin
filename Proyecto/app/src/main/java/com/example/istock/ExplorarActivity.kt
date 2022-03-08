package com.example.istock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.istock.model.BProductos
import com.example.istock.model.Productos
import com.example.istock.utils.Utils.mListaProductos
import com.example.istock.utils.Utils.referencia
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_explorar.*
import kotlinx.android.synthetic.main.activity_explorar.bottom_navigation
import kotlinx.android.synthetic.main.activity_profile.*

class ExplorarActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ProductosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explorar)
        bottom_navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    val i = Intent(this, HomeActivity::class.java)
                    startActivity(i);
                }
                R.id.page_2 -> {
                    val i = Intent(this, CarritoActivity::class.java)
                    startActivity(i);
                }
                R.id.page_3 -> {

                }
                R.id.page_4 -> {
                    val i = Intent(this, ProfileActivity::class.java)
                    startActivity(i);
                }
            }
            true
        }
        val aux = ArrayList<BProductos>()
        referencia.get()
            .addOnSuccessListener { result ->
                for (document in result){
                    aux.add(BProductos(
                        document.data.get("nombre").toString(),
                        document.data.get("descripcion").toString(),
                        document.data.get("stock").toString().toDouble(),
                        document.data.get("categoria").toString(),
                        document.data.get("precio").toString().toDouble(),
                        document.data.get("enlace").toString()
                    )
                    )
                }
                adapter = ProductosAdapter(this, aux)
                recyclerView_productos.adapter = adapter
                linearLayoutManager = LinearLayoutManager(this)
                recyclerView_productos.layoutManager = linearLayoutManager
            }
        if (aux.size > mListaProductos.size)
            mListaProductos = aux

    }
}