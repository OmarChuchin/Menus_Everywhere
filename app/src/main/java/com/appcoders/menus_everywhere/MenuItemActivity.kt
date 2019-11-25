package com.appcoders.menus_everywhere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_menu.*
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
        //Toast.makeText(applicationContext,alimento.platillos.size.toString(),Toast.LENGTH_LONG).show()
        val nombre =alimento.nombre
        itemNameText.setText(nombre)
        val adapter = MenuItemAdapter(this@MenuItemActivity,alimento.platillos.toTypedArray())
        rvMenuItem.adapter = adapter

    }
}
