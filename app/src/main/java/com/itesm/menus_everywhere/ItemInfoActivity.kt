package com.itesm.menus_everywhere

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_item_info.*

class ItemInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_info)

        itemInfoTitle.setText(intent.getStringExtra("nombrePlatillo"))
        itemDescription.setText(intent.getStringExtra("descripcion"))
        //itemCalories.setText("Calorías: "+intent.getStringExtra("calorias"))
        itemPrice.setText(intent.getStringExtra("precio"))
        if(intent.getStringExtra("nombrePlatillo").toLowerCase().contains("hamburguesa")){
            imageFood.setImageResource(R.drawable.img_hamburgesa2)
        }else if(intent.getStringExtra("nombrePlatillo").toLowerCase().contains("indio")){
            imageFood.setImageResource(R.drawable.beer)
        }else{
            imageFood.setImageResource(R.drawable.food_holder)
        }

        botonOrdenar.setOnClickListener { view ->
            val builder = AlertDialog.Builder(this@ItemInfoActivity)

            // Set the alert dialog title
            builder.setTitle("El producto se ha ordenado")

            // Display a message on alert dialog
            builder.setMessage("Tu producto se ha ordenado. Llegará a la mesa en un momento")

            // Display a neutral button on alert dialog
            builder.setPositiveButton("OK"){_,_ ->

            }

            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
        }
    }
}
