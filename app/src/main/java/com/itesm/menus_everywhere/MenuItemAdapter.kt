package com.itesm.menus_everywhere

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.element_menu_item.view.*
import kotlinx.android.synthetic.main.element_restaurant.view.*


class MenuItemAdapter(val context: Context, val arrayMenu: Array<Platillo>, val arrImagenes:Array<Int>): RecyclerView.Adapter<MenuItemAdapter.MenuItemCard>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemCard {
        val view = LayoutInflater.from(context).inflate(R.layout.element_menu_item,parent,false)
        return MenuItemCard(view)

    }

    override fun getItemCount(): Int {
        return arrayMenu.size

    }

    override fun onBindViewHolder(holder: MenuItemCard, position: Int) {
        val card = arrayMenu[position].nombre
        var imagen:Int;
        try{
            imagen = arrImagenes[position];
        }catch (e : ArrayIndexOutOfBoundsException){
            imagen = R.drawable.food_holder
        }
        holder.set( card,holder.view.context,position, imagen)

    }

    inner class MenuItemCard(var view :View): RecyclerView.ViewHolder(view){

        fun set(card :String, context: Context, position: Int, imagen:Int) {
           // Toast.makeText(context,"set",Toast.LENGTH_LONG).show()
            this.view.menuItemButton.text = card
            this.view.menuItemImage.setImageResource(imagen);
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

