package com.appcoders.menus_everywhere

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.OnCanceledListener
import com.google.android.gms.vision.text.Line
import com.google.firebase.FirebaseError
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_menu.*


class MenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        /*Firebase interaction*/
        val extras = intent.extras?.getString("QRScannedValue") //get restaurant key off QRScanner
        val fbRestauranteInstance = FirebaseDatabase.getInstance()
        var restaurantQuery = fbRestauranteInstance.getReference().child("Restaurante").orderByKey()
        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        rvMenu.layoutManager = layout
        val menuSubsectionsArray = mutableListOf<String>()


        /*The following regex checks for extras!=null and queries for its childs key*/
        extras?.let {
            restaurantQuery.ref.child(it).addValueEventListener(object : ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                    restaurantNameText.setText(p0.child("Nombre").getValue().toString())
                    for(restaurantRegister in p0.children) {
                        if(restaurantRegister.key == "Menú") {
                            for(menuRegister in restaurantRegister.children){
                                //Toast.makeText(applicationContext,menuRegister.key.toString(),Toast.LENGTH_LONG).show()
                                menuSubsectionsArray.add(menuRegister.key.toString())
                                Toast.makeText(applicationContext,menuSubsectionsArray.toString(),Toast.LENGTH_LONG).show()
                                val adapter = MenuAdapter(this@MenuActivity,menuSubsectionsArray.toTypedArray())
                                rvMenu.adapter = adapter

                            }
                        }

                    }
                }
                override fun onCancelled(p0: DatabaseError) {
                    Toast.makeText(applicationContext,"Algo ha pasado mal",Toast.LENGTH_LONG).show()
                    TODO("some more precise cases")
                }
            })

        }

        /*Fetch each element of the Menu*/




    }


}





