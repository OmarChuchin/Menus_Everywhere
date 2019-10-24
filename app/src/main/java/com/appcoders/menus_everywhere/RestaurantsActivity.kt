package com.appcoders.menus_everywhere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_restaurants.*

// Clase para crear la actividad de restaurante después es escanear un código QR.

class RestaurantsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)
        btnHamburguesa.setOnClickListener { view ->
            val intMenu = Intent(this,MenuActivity::class.java);
            startActivity(intMenu);
        }
    }
}
