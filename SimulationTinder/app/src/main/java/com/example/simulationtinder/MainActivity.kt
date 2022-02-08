package com.example.simulationtinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.lang.Math.abs
import java.util.*


class MainActivity : AppCompatActivity() {
    var timeEnd=0;

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)

       //var timeStart = Calendar.getInstance().time.seconds;

       //while (abs(timeStart - timeEnd) <= 10) {
         //  var timeEnd = Calendar.getInstance().time.seconds;
         //  Log.i("start", "${timeStart} --- ${timeEnd}")
       //}
       //if (abs(timeStart - timeEnd) >= 5) {
         //  Log.i("start", "Ingresando...")
           startActivity(Intent(this, Inicio::class.java))
       //}
   }




}