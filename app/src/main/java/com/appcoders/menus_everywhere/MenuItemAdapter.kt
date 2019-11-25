package com.appcoders.menus_everywhere

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

import kotlinx.android.synthetic.main.element_menu_item.view.*


class MenuItemAdapter(val context: Context, val arrayMenu: Array<Platillo>): RecyclerView.Adapter<MenuItemAdapter.MenuItemCard>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemCard {
        val view = LayoutInflater.from(context).inflate(R.layout.element_menu_item,parent,false)
        return MenuItemCard(view)

    }

    override fun getItemCount(): Int {
        return arrayMenu.size

    }

    override fun onBindViewHolder(holder: MenuItemCard, position: Int) {
        val card = arrayMenu[position].nombre
        holder.set( card,holder.view.context,position)

    }

    inner class MenuItemCard(var view :View): RecyclerView.ViewHolder(view){

        fun set(card :String, context: Context, position: Int) {
           // Toast.makeText(context,"set",Toast.LENGTH_LONG).show()
            this.view.menuItemButton.text = card
            this.view.menuItemButton.setOnClickListener { view ->


                val intent = Intent(view.context, ItemInfoActivity::class.java)

                intent.putExtra("calorias", arrayMenu.get(position).calorias)
                intent.putExtra("descripcion", arrayMenu.get(position).descripcion)
                intent.putExtra("precio", arrayMenu.get(position).precio)
                intent.putExtra("nombrePlatillo", arrayMenu.get(position).nombre)

                startActivity(view.context,intent,null)

            }
        }
    }

}

