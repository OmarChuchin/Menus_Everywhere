package com.appcoders.menus_everywhere.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.appcoders.menus_everywhere.RestaurantAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(com.appcoders.menus_everywhere.R.layout.fragment_home, container, false)
        val fbRestauranteInstance = FirebaseDatabase.getInstance()
        val restaurantQuery = fbRestauranteInstance.getReference().child("Restaurante").orderByKey()
        val layout = LinearLayoutManager(inflater.context)
        layout.orientation = LinearLayoutManager.VERTICAL
        root.listaRestaurante.layoutManager = layout
        val restaurantSubsectionsArray = mutableListOf<String>()

        restaurantQuery.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(inflater.context,"Hubo un error obteniendo la base de datos",Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (child in dataSnapshot.getChildren()) {
                    val post = child;
                    if (post != null) {
                        println(post.key)
                        restaurantSubsectionsArray.add(post.child("Nombre").value.toString())
                        val adapter = RestaurantAdapter(inflater.context,restaurantSubsectionsArray.toTypedArray(),post.key!!.toInt())
                        root.listaRestaurante.adapter = adapter
                    }

                }
            }

        })

        return root
    }
}