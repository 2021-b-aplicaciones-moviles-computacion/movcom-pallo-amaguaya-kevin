package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CRestaurante : AppCompatActivity() {
    var query: Query? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crestaurante)

        val button_consultar = findViewById<Button>(R.id.btn_consultar)
        button_consultar.setOnClickListener {
            consultar()
        }
        val button_datosPrueba = findViewById<Button>(R.id.btn_datos_prueba)
        button_consultar.setOnClickListener {
            crearDatosPrueba()
        }
        val button_crear = findViewById<Button>(R.id.btn_crear_restaurante)
        button_consultar.setOnClickListener {
            crearRestaurante()
        }


    }

    fun consultar(){

/*
        // 1) Consultar varios documentos
        val db = Firebase.firestore
        val citiesRef = db
            .collection(  "cities")
            .orderBy( "population")
            .limit(  2) // solo tomamos 2 reg

        citiesRef
            // .document(”BJ") // ID
            .get()
            .addOnSuccessListener {
                for (ciudad in it){
                    Log.i("consultas", "${ciudad.data} ${ciudad.id}")
                }
            }
            .addOnFailureListener { }

        // Consultar 1 documento por id
        val citiesRernico = db
            .collection( "cities")
        // .0rder8y("population") // N0 USANUS C
            // .limit(2) // N0 usamos CON DOCUMENT z
        citiesRernico
            .document(  "BJ") // ID I
            .get()
            .addOnSuccessListener {
                Log.i(  "consultas",  "${it.data}")
                }
                .addOnFailureListener { }

        // 3) Buscar‘ por‘ un solo campo ==
        citiesRef
            .whereEqualTo( "country", "China")
            .get()
            .addOnSuccessListener {
                Log.i("consu1tas", "${it.documents}")
                for (ciudad in it){
                    Log.i("consultas 2:", "${ciudad.data}")
                    Log.i("consultas =2P", "${ciudad.id}")
                }
            }
            .addOnFailureListener { }

        // 4) Buscor por dos 0 mos elementos compo '::' 'arrag-contains‘
        citiesRef
            .whereEqualTo( "capital", false)
            .whereArrayContainsAny( "regions", arrayListOf("socal", "norcal"))
            .get()
            .addOnSuccessListener {
                for (ciudad in it){
                    Log.i("consultas",  "== array-contains ${ciudad.data}")
                }
            }

        // 5) Buscar por dos o mas elementos campo "==" "mayorigual"
        citiesRef
            .whereEqualTo("capital", true)
            .whereGreaterThanOrEqualTo("population", 1000000)
            .get()
            .addOnSuccessListener {
                for(ciudad in it){
                    Log.i("consultas",  "== array-contains ${ciudad.data}")
                }
            }

        // 6) Buscar por dos o mas elementos campo "==" "menorigual"
        citiesRef
            .whereEqualTo("capital", false)
            .whereLessThanOrEqualTo("population", 4000000)
            .orderBy("population", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                for(ciudad in it){
                    Log.i("consultas",  "== array-contains ${ciudad.data}")
                }
            }
*/
        //PAGINACION
        val db = Firebase.firestore
        val refCities = db
            .collection("cities")
            .orderBy("population")
            .limit(2)
        var tarea: Task<QuerySnapshot>? = null
            if (query == null)
                tarea = refCities.get()
            else
                tarea = query!!.get()
            if (tarea != null){
                tarea
                    .addOnSuccessListener { documentSnapshots ->
                        guardarQuery(documentSnapshots, refCities)
                        for(ciudad in documentSnapshots){
                            Log.i("consultas", "${ciudad.data}")
                        }
                    }
                    .addOnSuccessListener {
                        Log.i("consultas", "Error ${it}")
                    }
            }
    }

    fun guardarQuery(documentSnapshot: QuerySnapshot, refCities: Query){
        if(documentSnapshot.size() > 0 ){
            val ultimoDocumento = documentSnapshot.documents[documentSnapshot.size()-1]
            query = refCities
                .startAfter(ultimoDocumento)
        }else{

        }
    }
    fun transaccion(){}
    fun crearRestaurante(){
        val editTextNombre = findViewById<EditText>(R.id.editText_nombreRestaurant)
        val nuevoRestaunte = hashMapOf<String, Any>(
            "nombre" to editTextNombre.text.toString()
        )
        val db= Firebase.firestore
        val referencia =  db.collection("restaurante")
        referencia.
        add(nuevoRestaunte)
            .addOnSuccessListener {
                editTextNombre.text.clear()
            }
            .addOnFailureListener{

            }

    }
    fun crearDatosPrueba(){
        val db = Firebase.firestore
        val cities = db.collection("cities")

        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal")
        )
        cities.document("SF").set(data1)
        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal")
        )
        cities.document("LA").set(data2)
        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast")
        )
        cities.document("DC").set(data3)
        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu")
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei")
        )
        cities.document("BJ").set(data5)

    }
}