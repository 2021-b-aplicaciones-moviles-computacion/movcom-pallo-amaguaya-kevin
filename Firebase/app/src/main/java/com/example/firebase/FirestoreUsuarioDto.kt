package com.example.firebase

import android.widget.Button

data class FirestoreUsuarioDto(
    var uid: String = "",
    var email: String = "",
    var roles: ArrayList<String> = arrayListOf()
) {


}