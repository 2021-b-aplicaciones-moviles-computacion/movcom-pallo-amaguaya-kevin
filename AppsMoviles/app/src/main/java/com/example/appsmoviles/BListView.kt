package com.example.appsmoviles

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class BListView : AppCompatActivity() {

    var idItemSelected = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val listView = findViewById<ListView>(R.id.lv_list_view)
        val arreglo: ArrayList<Int> = arrayListOf(1,2,3,4,5)

        val adaptador = ArrayAdapter(
            this, //Contexto
            android.R.layout.simple_list_item_1, // como se va a ver en el XML
            //arreglo Arreglo

            BBaseDatosMemoria.arregloBEntrenador
        )

        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val btnAnadirListView = findViewById<Button>(R.id.btn_anadir_list_view) //<>
        btnAnadirListView
            .setOnClickListener{
                anadirItem(adaptador,BBaseDatosMemoria.arregloBEntrenador,1)
            }

/*        listView.setOnItemLongClickListener{parent,view, position, id ->
            Log.i("list-view", "LONG CLICK ${arreglo[position]}" )
            return@setOnItemLongClickListener true
        }*/

        //1.) Registron el menu contextual
        registerForContextMenu(listView)

    }
    // 2. Seleccionar el XML a usar
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        Log.i("context-menu","Posicion: ${id}");
    }


    // 3. Que opción se selecciona
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.m_editar->{
                Log.i("context-menu","Editar Poisicion ${idItemSelected}")
                abrirDialogo()
                return true;
            }
            R.id.m_eliminar->{
                Log.i("context-menu","Editar Poisicion ${idItemSelected}")
                return true;
            }
            else -> super.onContextItemSelected(item)
        }
    }

    //Abrir el dialogo
    fun abrirDialogo(){
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Titulo")
        //builder.setMessage("Hola que hace")

        //Botones
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                dialog, which -> Log.i("dialog", "Hola k hace")
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val opciones = resources.getStringArray(
            R.array.string_array_opciones_dialogo
        )

        val seleccionPrevia = booleanArrayOf(
            true, false, false
        )

       builder.setMultiChoiceItems(
            opciones,
            seleccionPrevia,{
                dialog,
                which,
                    isChecked ->
                    Log.i("dialogo", "Dio click en el item ${which}")
            }
        )


        val dialog = builder.create()
        dialog.show()
    }

    fun anadirItem(
        adaptador: ArrayAdapter<BEntrenador>,
        arreglo: ArrayList<BEntrenador>,
        valor:Int
    ){
        arreglo.add(BEntrenador("otro", "o@o.com"))
        adaptador.notifyDataSetChanged()
    }
}