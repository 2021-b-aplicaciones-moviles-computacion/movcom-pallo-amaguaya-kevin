package com.example.istock

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecyclerViewCategorias(
    private val contexto: HomeActivity,
    private val listaCategoria: List<Categorias>,
    private val recyclerView: RecyclerView
):RecyclerView.Adapter<RecyclerViewCategorias.MyViewHolder>() {

    var listProd = ArrayList<Productos>()

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val categoriaName: TextView
        val recyclierViewProd:RecyclerView

        init{
            categoriaName = view.findViewById(R.id.textView_Productos)
            recyclierViewProd = view.findViewById(R.id.recycler_viewHor_prod)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.productos_recycler_view,
                parent,
                false,
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.categoriaName.text = listaCategoria[position].nombre
        val db=Firebase.firestore

        //var recyclierViewProd = recyclerView.findViewById<RecyclerView>(R.id.recycler_viewHor_prod)

        db.collection("Categoria")
            .document("${listaCategoria[position].id_Categorias}")
            .collection("${listaCategoria[position].nombre}")
            .get()
            .addOnSuccessListener { result ->
                listProd.clear()
                for(documentos in result ){
                    listProd.add( Productos(
                        documentos.id,
                        documentos.data.get("nombre").toString(),
                        documentos.data.get("valorUnitario").toString(),
                        documentos.data.get("cantidad").toString(),
                        documentos.data.get("enlace").toString()
                    )
                    )
                }

                val adaptador = RecyclerViewProductos_Home(
                    this,
                    listProd,
                    holder.recyclierViewProd
                )
                holder.recyclierViewProd.adapter = adaptador
                holder.recyclierViewProd.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
                holder.recyclierViewProd.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this.contexto, LinearLayoutManager.HORIZONTAL, false)
                adaptador.notifyDataSetChanged()
                contexto.registerForContextMenu(holder.recyclierViewProd)
            }
            .addOnFailureListener {
                Log.i("recyclerView_H","Fallo el recycler")
            }
    }

    override fun getItemCount(): Int {
        return listaCategoria.size
    }

}