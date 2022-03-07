package com.example.istock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.istock.views.CreateAccountActivityy
import com.example.istock.utils.FirebaseUtils.firebaseAuth
import com.example.istock.extensions.Extensions.toast
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.istock.extensions.Extensions.toast
import com.example.istock.utils.FirebaseUtils.firebaseAuth
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private var adaptador: ArrayAdapter<String?>? = null
    var categoriasList = ArrayList<Categorias>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottom_navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {

                }
                R.id.page_2 -> {
                }
                R.id.page_3 -> {
                    val i = Intent(this, ExplorarActivity::class.java)
                    startActivity(i);
                }
                R.id.page_4 -> {
                    val i = Intent(this, ProfileActivity::class.java)
                    startActivity(i);
                }
            }
            true
        }

        var recyclierViewCategorias = findViewById<RecyclerView>(R.id.listView_Categorias)
        var db = Firebase.firestore

        db.collection("Categoria")
            .get()
            .addOnSuccessListener {result ->
                for (document in result) {
                    categoriasList.add(Categorias(
                        document.id.toString(),
                        document.data.get("nombre").toString(),
                    )
                    )
                }
                Log.i("categoria","${categoriasList}")
                val adaptador = RecyclerViewCategorias(
                    this,
                    categoriasList,
                    recyclierViewCategorias
                )
                recyclierViewCategorias.adapter = adaptador
                recyclierViewCategorias.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
                recyclierViewCategorias.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
                adaptador.notifyDataSetChanged()
                registerForContextMenu(recyclierViewCategorias)
            }
            .addOnFailureListener {
                Log.i("categoria","No se pudo realizar la accion")
            }
        }
    }

