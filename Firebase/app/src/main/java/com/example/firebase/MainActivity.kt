package com.example.firebase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Empezar a hacer el login
        val btn_login = findViewById<Button>(R.id.button_login)
        btn_login.setOnClickListener {
            llamarLoginUsuario()
        }

        //Salir
        val btn_logout = findViewById<Button>(R.id.button_logout)
        btn_logout.setOnClickListener {
            solicitarSalirDelAplicativo()
        }

        //Restaurante
        val btn_restaurante = findViewById<Button>(R.id.button_Restaurante)
        btn_restaurante.setOnClickListener {
            val intent = Intent(this, CRestaurante::class.java)
            startActivity(intent)
        }

        //Producto
        val btn_producto = findViewById<Button>(R.id.button_producto)
        btn_producto.setOnClickListener {
            val intent = Intent(this, CProducto::class.java)
            startActivity(intent)
        }
    }
    fun llamarLoginUsuario(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTosAndPrivacyPolicyUrls(
                    "https://example.com/terms.html",
                    "https://example.com/privacy.html"
                )
                .build(),
            200
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            200 -> {
                if(resultCode == Activity.RESULT_OK){
                    val usuario: IdpResponse? = IdpResponse.fromResultIntent(data)
                    if(usuario != null){
                        if(usuario.isNewUser == true){
                            Log.i("firebase-login", "Nuevo Usuario")
                            registrarUsuarioPorPrimeraVez(usuario)
                        }else{
                            Log.i("firebase-login", "Usuario antiguo")
                            setearUsuarioFirebase()
                        }
                    }
                }
                else{
                    Log.i("firebase-login", "El usuario canceló")
                }
            }
        }
    }

    fun registrarUsuarioPorPrimeraVez(usuario: IdpResponse){
        val usuarioLogeado = FirebaseAuth
            .getInstance()
            .getCurrentUser()
        if (usuario.email != null && usuarioLogeado != null){
            val db = Firebase.firestore
            val roles = arrayListOf("usuario")
            val email = usuario.email
            val uid = usuarioLogeado.uid

            val nuevoUsuario = hashMapOf<String, Any>(
                "roles" to roles,
                "uid" to uid,
                "email" to email.toString()
            )

            // Para el identificador del documento enviamos el email
            db.collection("usuario")
                .document(email.toString())
                .set(nuevoUsuario)
                .addOnSuccessListener {
                    Log.i("firebase-firestore", "Se creo")
                }
                .addOnFailureListener{
                    Log.i("firebase-firestore", "fallo")
                }
        }else{
            Log.i("firebase-login", "Error no email ni usuario")
        }
    }

    fun setearUsuarioFirebase(){
        val instanciaAuth = FirebaseAuth.getInstance()
        val usuarioLocal = instanciaAuth.currentUser
        if(usuarioLocal != null){
            if(usuarioLocal.email != null){
                val db = Firebase.firestore
                val referencia = db
                    .collection("usuario")
                    .document(usuarioLocal.email.toString())
                referencia
                    .get()
                    .addOnSuccessListener {
                        val usuarioCargado: FirestoreUsuarioDto? =
                            it.toObject(FirestoreUsuarioDto::class.java)
                        if(usuarioCargado != null){
                            BAuthUsuario.usuario = usuarioCargado
                        }
                        setearBienvenida()
                        Log.i("firebase-firestore", "Usuario cargado")
                    }
                    .addOnFailureListener{
                        Log.i("firebase-firestore", "Fallo cargar usuario")
                    }
                }
            }
        }

    fun setearBienvenida(){
        val textViewBienvenida = findViewById<TextView>(R.id.textView)
        val btn_login = findViewById<Button>(R.id.button_login)
        val btn_logout = findViewById<Button>(R.id.button_logout)
        val btn_restaurante = findViewById<Button>(R.id.button_Restaurante)
        val btn_producto = findViewById<Button>(R.id.button_producto)

        if(BAuthUsuario.usuario != null){
            textViewBienvenida.text = "Bienveido ${BAuthUsuario.usuario?.email}"
            btn_login.visibility = View.INVISIBLE
            btn_logout.visibility = View.VISIBLE
            btn_restaurante.visibility = View.VISIBLE
            btn_producto.visibility = View.VISIBLE
        }else{
            textViewBienvenida.text = "Ingresa al aplicativo"
            btn_login.visibility = View.VISIBLE
            btn_logout.visibility = View.INVISIBLE
            btn_restaurante.visibility = View.INVISIBLE
            btn_producto.visibility = View.INVISIBLE
        }
    }

    fun solicitarSalirDelAplicativo(){
        AuthUI
            .getInstance()
            .signOut(this)
            .addOnCompleteListener{
                BAuthUsuario.usuario = null
                setearBienvenida()
            }
    }
}
