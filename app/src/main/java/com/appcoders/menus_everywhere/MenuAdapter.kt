package com.appcoders.menus_everywhere

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.element_menu.view.*
import java.sql.Blob

class MenuAdapter(val context: Context, val arrayMenu: Array<String>): RecyclerView.Adapter<MenuAdapter.MenuCard>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuCard {
        val view = LayoutInflater.from(context).inflate(R.layout.element_menu,parent,false)
        return MenuCard(view)
    }

    override fun getItemCount(): Int {
        return arrayMenu.size
    }

    override fun onBindViewHolder(holder: MenuCard, position: Int) {
        val card = arrayMenu[position]
        holder.set( card)
    }

    inner class MenuCard(var view :View): RecyclerView.ViewHolder(view){
        fun set(card :String) {
            view.menuButton.text = card;
        }

    }
}
