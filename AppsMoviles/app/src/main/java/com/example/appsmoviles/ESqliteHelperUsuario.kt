package com.example.appsmoviles

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteHelperUsuario(contexto: Context?): SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaUsuario =
            """
                CREATE TABLE USUARIO (
                id INTEGER PRIMARY KEY AUTOINCREMET,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
                )
            """.trimIndent()
        Log.i("bbd", "Creando una tabla de nombre Usuario")
        db?.execSQL(scriptCrearTablaUsuario)
    }
    fun crearUsuarioFormulario(
        nombre:String,
        descripcion: String
    ):Boolean {
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion",descripcion)
        val resultadoEscritura : Long = baseDatosEscritura.insert(
            "USUARIO",
            null,
            valoresAGuardar
        )
        baseDatosEscritura.close()
        return if (resultadoEscritura.toInt() == -1 ) false else true
    }

    fun consultarUsuarioFormulario(id: Int): EUsuarioBDD{
        val baseDatosLectura= readableDatabase
        val scriptConsultarUsuario= "SELECT * FROM USUARIO WHERE id = ${id}"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = EUsuarioBDD(0,"","")
        if(existeUsuario){
            do{
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val descripcion = resultadoConsultaLectura.getString(2)
                if(id != null ){
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                }
            }while(resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }


    fun eliminarUsuarioFormulario(id: Int):Boolean{
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura.delete(
            "USUARIO",
            "id=?",
            arrayOf(
                id.toString()
            )
        )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1 ) false else true
    }


    fun actualizarUsuarioFormulario(
        idAcutualizar:Int,
        nombre: String,
        descripcion: String
        ):Boolean{
            val conexionEscritura = writableDatabase
            val valoresActualizar = ContentValues()
            valoresActualizar.put("nombre", nombre)
            valoresActualizar.put("descripcion", descripcion)
            val resultadoActualizar = conexionEscritura.update(
                "USUARIO",
                valoresActualizar,
                "id=?",
                arrayOf(
                        idAcutualizar.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualizar.toInt() == -1 ) false else true
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}