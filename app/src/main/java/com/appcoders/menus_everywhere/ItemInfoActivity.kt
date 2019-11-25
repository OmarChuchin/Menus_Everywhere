package com.appcoders.menus_everywhere

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_item_info.*

class ItemInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_info)

        itemInfoTitle.setText(intent.getStringExtra("nombrePlatillo"))
        itemDescription.setText(intent.getStringExtra("descripcion"))
        itemCalories.setText(intent.getStringExtra("calorias"))
        itemPrice.setText(intent.getStringExtra("precio"))




    }
}
