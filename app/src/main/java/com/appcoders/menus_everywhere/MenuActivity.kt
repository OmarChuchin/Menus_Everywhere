package com.appcoders.menus_everywhere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        buttonSencilla.setOnClickListener { view ->
            val intProduct = Intent(this, ProductActivity::class.java);
            startActivity(intProduct)
        }
        val extras = intent.extras?.getString("QRScannedValue") //get restaurant key off QRScanner
        val fbRestauranteInstance = FirebaseDatabase.getInstance()
        var restaurantQuery = fbRestauranteInstance.getReference().child("Restaurante").orderByKey()
        var nodeValue : String = ""
        /*The following regex checks for extras!=null and queries for its childs key*/
        val restauranteID = extras?.let {
            val restaurantIDDTO : RestauranteID

            val listener = object : ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                   nodeValue = p0.getValue().toString()
                    restaurantNameText.setText(p0.getValue().toString())

                    //interaccion con el modelo de ventana
                }
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            }
            restaurantQuery.ref.child(it).child("Nombre").addValueEventListener(listener)

        }
        Toast.makeText(applicationContext,nodeValue,Toast.LENGTH_LONG).show()


    }
}
