package com.itesm.menus_everywhere

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_menu_item.*

class MenuItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_item)

        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        rvMenuItem.layoutManager = layout


        val gson = Gson()

        val alimento = gson.fromJson<Alimento>(intent.getStringExtra("alimento"),
            Alimento::class.java)
        val nombre =alimento.nombre
        val imagenes = mutableListOf<Int>()
        if(nombre.toLowerCase().contains("hamburguesa")){
            imagenes.add(R.drawable.img_hamburgesa1)
        }else if(nombre.toLowerCase().contains("cervezas")){
            imagenes.add(R.drawable.beer)
        }
        else{
            imagenes.add(R.drawable.food_holder)
        }


        itemNameText.setText(nombre)
        val adapter = MenuItemAdapter(this@MenuItemActivity,alimento.platillos.toTypedArray(), imagenes.toTypedArray())
        rvMenuItem.adapter = adapter

    }
}
