package com.example.istock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.istock.extensions.Extensions.toast
import com.example.istock.utils.Utils.mCarrito
import kotlinx.android.synthetic.main.activity_carrito.*

class CarritoActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: CarritoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)
        var suma = 0.0
        bottom_navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    val i = Intent(this, HomeActivity::class.java)
                    startActivity(i);
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
        val linearLayout  =findViewById<LinearLayout>(R.id.linearLayout)
        if (mCarrito.size == 0){
            linearLayout.isVisible = false
            imageView_carritovacio.isVisible = true
        }else{
            linearLayout.isVisible = true
            imageView_carritovacio.isVisible = false
            for (i in mCarrito)
                suma += i.precio!!
            textView_subtotal.setText((Math.round(suma * 1000.0)/1000.0).toString())
            adapter = CarritoAdapter( mCarrito, suma)
            recyclerView_carrito.adapter = adapter
            linearLayoutManager = LinearLayoutManager(this)
            recyclerView_carrito.layoutManager = linearLayoutManager
            suma+=1.50
            textView_totaL.setText((Math.round(suma * 1000.0)/1000.0).toString())
            btn_realizar_pedido.setOnClickListener {
                toast("Pedido realizado")
                mCarrito.clear()
                val i = Intent(this, CarritoActivity::class.java)
                startActivity(i)
                finish()
            }
        }



    }
}