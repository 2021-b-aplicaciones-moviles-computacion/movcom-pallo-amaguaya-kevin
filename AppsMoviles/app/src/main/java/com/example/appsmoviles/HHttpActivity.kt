package com.example.appsmoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import kotlin.Result.*
import com.github.kittinunf.result.Result

class HHttpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hhttp)
        metodoGet()
    }

    fun metodoGet() {
        val indentificadorPost = 1
        val url = "https://jsonplaceholder.typicode.com/posts/" + indentificadorPost.toString()
        url
            .httpGet()
            .responseString { req, res, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val respuestaString = result.get()
                        Log.i("http-klaxon", "${respuestaString}")

                    }
                }

            }
    }

    fun metodoPost() {
        val parametros: List<Pair<String, *>> = listOf(
            "tittle" to "Titulo moviles",
            "body" to "descripcion moviles",
            "userId" to 1
        )
        val url = "https://jsonplaceholder.typicode.com/posts"
        url
            .httpPost(parametros)
            .responseString { req, res, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val postString = result.get()
                        Log.i("http-klaxon", "${postString}")
                        val post = Klaxon()
                            .parse<IPostHtto>(postString)
                        Log.i("http-klaxon", "Error: ${post?.title}")
                    }
                }
            }
    }
}