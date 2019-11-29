package com.itesm.menus_everywhere

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_menu.*
import android.view.MotionEvent


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
        val imagenes = mutableListOf<Int>()
        //imagenes.add(R.drawable.img_hamburgesa1)


        /*The following regex checks for extras!=null and queries for its childs key*/
        extras?.let {
            restaurantQuery.ref.child(it).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    //extract name and set it in display
                    val restaurant = RestauranteID(p0.child("nombre").getValue().toString())
                    val menu = Menu()
                    restaurantNameText.setText(restaurant.nombre)

                    val tamanio = p0.child("Menú").children.asIterable().toList().size;
                    if(tamanio > 0) {
                        for (alimento in p0.child("Menú").children) {

                            val currentAlimento = Alimento(alimento.key.toString())
                            if(currentAlimento.nombre.toLowerCase().contains("hamburguesa")){
                                imagenes.add(R.drawable.img_hamburgesa1)
                            }else{
                                imagenes.add(R.drawable.food_holder)
                            }
                            for (platillo in alimento.children) { //creaate a dto and populate with external data
                                val currentPlatillo = Platillo(
                                    platillo.key.toString(),
                                    platillo.child("Calorias").value.toString(),
                                    platillo.child("Descripción").value.toString(),
                                    platillo.child("Precio").value.toString()
                                )
                                currentAlimento.platillos.add(currentPlatillo)
                            }

                            menu.alimentos.add(currentAlimento)

                            menuSubsectionsArray.add(currentAlimento.nombre)
                            //Toast.makeText(applicationContext,menuSubsectionsArray.toString(),Toast.LENGTH_LONG).show()

                            val adapter = MenuAdapter(
                                this@MenuActivity,
                                menu.alimentos.toTypedArray(),
                                imagenes.toTypedArray()
                            )
                            rvMenu.adapter = adapter

                        }
                    }else{
                        val builder = AlertDialog.Builder(this@MenuActivity)
                        builder.setTitle("No se encontraron elementos del menú")
                        builder.setNeutralButton("OK"){_,_ ->
                            finish()
                        }
                        val dialog: AlertDialog = builder.create()

                        // Display the alert dialog on app interface
                        dialog.show()

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

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // If we've received a touch notification that the user has touched
        // outside the app, finish the activity.
        if (MotionEvent.ACTION_OUTSIDE == event.action) {
            finish()
            return true
        }

        // Delegate everything else to Activity.
        return super.onTouchEvent(event)
    }

}





