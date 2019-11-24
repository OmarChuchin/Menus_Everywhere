package com.appcoders.menus_everywhere

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.element_menu.view.*
import java.sql.Blob
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.nio.file.Files.delete
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView.inflate
import com.google.gson.Gson


class MenuAdapter(val context: Context, val arrayMenu: Array<Alimento>): RecyclerView.Adapter<MenuAdapter.MenuCard>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuCard {
        val view = LayoutInflater.from(context).inflate(R.layout.element_menu,parent,false)
        return MenuCard(view)
    }

    override fun getItemCount(): Int {
        return arrayMenu.size
    }

    override fun onBindViewHolder(holder: MenuCard, position: Int) {
        val card = arrayMenu[position].nombre
        holder.set( card,holder.view.context)
    }

    inner class MenuCard(var view :View): RecyclerView.ViewHolder(view){
        fun set(card :String, context: Context) {
            view.menuButton.text = card
            view.menuButton.setOnClickListener { view ->
                //Toast.makeText(context,card,Toast.LENGTH_LONG).show()
                val gson = Gson()
                val intent = Intent(view.context, MenuItemActivity::class.java)
                intent.putExtra("identifier", gson.toJson(arrayMenu))
                startActivity(view.context,intent,null)

                //todo: ligar a activity menu xml
                //todo: pasar argumentos para query de activity menu


            }
            view.imageView2.setImageResource(R.drawable.img_hamburgesa2);
        }
    }


}
