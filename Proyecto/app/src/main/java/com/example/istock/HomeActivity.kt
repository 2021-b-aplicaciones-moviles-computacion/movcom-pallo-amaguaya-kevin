package com.example.istock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.istock.extensions.Extensions.toast
import com.example.istock.utils.FirebaseUtils.firebaseAuth
import com.example.istock.views.CreateAccountActivityy
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnSignOut.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, CreateAccountActivityy::class.java))
            toast("signed out")
            finish()
        }
    }
}