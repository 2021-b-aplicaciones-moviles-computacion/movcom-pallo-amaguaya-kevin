package com.example.istock.utils

import com.example.istock.model.BProductos
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object Utils {
    val db = Firebase.firestore
    val referencia = db.collection("productos")
    var mListaProductos = ArrayList<BProductos>()
    val mCarrito = ArrayList<BProductos>()
}