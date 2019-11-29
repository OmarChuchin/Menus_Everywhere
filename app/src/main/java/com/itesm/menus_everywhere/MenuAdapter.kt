package com.itesm.menus_everywhere

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.element_menu.view.*
import androidx.core.content.ContextCompat.startActivity
import com.google.gson.Gson



class MenuAdapter(val context: Context, val arrayMenu: Array<Alimento>, val arrayImage: Array<Int>): RecyclerView.Adapter<MenuAdapter.MenuCard>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuCard {
        val view = LayoutInflater.from(context).inflate(R.layout.element_menu,parent,false)
        return MenuCard(view)
    }

    override fun getItemCount(): Int {
        return arrayMenu.size
    }

    override fun onBindViewHolder(holder: MenuCard, position: Int) {
        val card = arrayMenu[position].nombre
        var imagen: Int;
        try {
            imagen = arrayImage[position]
        }catch (e : ArrayIndexOutOfBoundsException){
            imagen = R.drawable.food_holder
        }
        holder.set( card,holder.view.context,position, imagen)
    }

    inner class MenuCard(var view :View): RecyclerView.ViewHolder(view){

        fun set(card :String, context: Context, position: Int, imagen: Int) {
            view.menuButton.text = card
            view.menuButton.setOnClickListener { view ->
                val gson = Gson()
                val intent = Intent(view.context, MenuItemActivity::class.java)
                intent.putExtra("alimento", gson.toJson(arrayMenu.get(position)))
                startActivity(view.context,intent,null)
            }

            view.imageView2.setImageResource(imagen)
            //Toast.makeText(context,cIndex,Toast.LENGTH_LONG).show()

        }
    }


}
