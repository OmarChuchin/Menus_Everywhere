package com.appcoders.menus_everywhere

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MenuItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_item)
        Toast.makeText(applicationContext,"weeeeeeeeee",Toast.LENGTH_LONG).show()
    }
}
