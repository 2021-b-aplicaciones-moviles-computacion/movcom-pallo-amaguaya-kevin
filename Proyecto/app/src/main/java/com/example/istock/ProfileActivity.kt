package com.example.istock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.istock.extensions.Extensions.toast
import com.example.istock.utils.FirebaseUtils
import com.example.istock.views.CreateAccountActivityy
import com.example.istock.views.SignInActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.bottom_navigation

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        btnSignOut.setOnClickListener {
            FirebaseUtils.firebaseAuth.signOut()
            startActivity(Intent(this, SignInActivity::class.java))
            toast("signed out")
            finish()
        }
        bottom_navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    val i = Intent(this, HomeActivity::class.java)
                    startActivity(i);
                }
                R.id.page_2 -> {
                    val i = Intent(this, CarritoActivity::class.java)
                    startActivity(i);
                }
                R.id.page_3 -> {
                    val i = Intent(this, ExplorarActivity::class.java)
                    startActivity(i);
                }
                R.id.page_4 -> {
                }
            }
            true
        }
    }
}