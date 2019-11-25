package com.appcoders.menus_everywhere

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_menu.*


class MenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        /*Firebase interaction*/
        val extras = intent.extras?.getString("QRScannedValue") //get restaurant key off QRScanner
        val fbRestauranteInstance = FirebaseDatabase.getInstance()
        val restaurantQuery = fbRestauranteInstance.getReference().child("Restaurante").orderByKey()
        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        rvMenu.layoutManager = layout
        val menuSubsectionsArray = mutableListOf<String>()


        /*The following regex checks for extras!=null and queries for its childs key*/
        extras?.let {
            restaurantQuery.ref.child(it).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    //extract name and set it in display
                    val restaurant = RestauranteID(p0.child("nombre").getValue().toString())
                    val menu = Menu()
                    restaurantNameText.setText(restaurant.nombre)

                    for (alimento in p0.child("Menú").children) {

                        val currentAlimento = Alimento(alimento.key.toString())

                        for (platillo in alimento.children){ //creaate a dto and populate with external data
                            val currentPlatillo =  Platillo(
                                platillo.key.toString(),
                                platillo.child("Calorias").value.toString(),
                                platillo.child("Descripción").value.toString(),
                                platillo.child("Precio").value.toString())
                            currentAlimento.platillos.add(currentPlatillo)
                        }

                        menu.alimentos.add(currentAlimento)

                        menuSubsectionsArray.add(currentAlimento.nombre)
                        //Toast.makeText(applicationContext,menuSubsectionsArray.toString(),Toast.LENGTH_LONG).show()

                        val adapter =
                            MenuAdapter(this@MenuActivity,menu.alimentos.toTypedArray())
                        rvMenu.adapter = adapter

                    }

                }

                override fun onCancelled(p0: DatabaseError) {

                    Toast.makeText(applicationContext, "Algo ha pasado mal", Toast.LENGTH_LONG)
                        .show()
                    TODO("some more precise cases")

                }
            })

        }

        /*Fetch each element of the Menu*/


    }


}





