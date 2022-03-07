package com.example.istock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
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

        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.item1 -> {
                    startActivity(Intent(this, Buscar::class.java))
                    true
                }
                R.id.item2 -> {
                    // Respond to navigation item 2 click
                    true
                }
                R.id.item3 -> {
                    // Respond to navigation item 2 click
                    true
                }
                R.id.item4 -> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
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

        //var btn_verTodos = findViewById<Button>(R.id.button_verTodos)
        //btn_verTodos.setOnClickListener {

        //}


        /*      BOTÓN DE LOGOUT

        btnSignOut.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, CreateAccountActivityy::class.java))
            toast("signed out")
            finish()
        }*/
    }


}