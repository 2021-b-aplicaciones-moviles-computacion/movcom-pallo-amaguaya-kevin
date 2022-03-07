package com.example.istock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.istock.extensions.Extensions.toast
import com.example.istock.utils.FirebaseUtils.firebaseAuth
import com.example.istock.views.CreateAccountActivityy
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
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
    }
}