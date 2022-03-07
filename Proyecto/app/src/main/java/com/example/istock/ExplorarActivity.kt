package com.example.istock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.istock.model.Productos
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_explorar.*
import kotlinx.android.synthetic.main.activity_explorar.bottom_navigation
import kotlinx.android.synthetic.main.activity_profile.*

class ExplorarActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ProductosAdapter
    val db = Firebase.firestore
    val referencia = db.collection("productos")
    val mListaProductos = ArrayList<Productos>()
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
        referencia.get()
            .addOnSuccessListener { result ->
                for (document in result){
                    mListaProductos.add(Productos(
                        document.data.get("nombre").toString(),
                        document.data.get("descripcion").toString(),
                        document.data.get("stock").toString().toDouble(),
                        document.data.get("categoria").toString(),
                        document.data.get("precio").toString().toDouble(),
                        document.data.get("enlace").toString()
                    )
                    )
                }
                Log.d("aaa", "$mListaProductos")
                adapter = ProductosAdapter(mListaProductos)
                recyclerView_productos.adapter = adapter
                linearLayoutManager = LinearLayoutManager(this)
                recyclerView_productos.layoutManager = linearLayoutManager

            }


    }
}