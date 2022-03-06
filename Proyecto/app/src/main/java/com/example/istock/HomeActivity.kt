package com.example.istock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.istock.extensions.Extensions.toast
import com.example.istock.utils.FirebaseUtils.firebaseAuth
import com.example.istock.views.CreateAccountActivityy
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.item1 -> {
                    // Respond to navigation item 1 click
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
        btnSignOut.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, CreateAccountActivityy::class.java))
            toast("signed out")
            finish()
        }
    }
}