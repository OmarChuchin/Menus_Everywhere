package com.appcoders.menus_everywhere

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.element_menu.view.*

class RestaurantAdapter (val context: Context, val arrayMenu: Array<String>, val value: Int): RecyclerView.Adapter<RestaurantAdapter.RestaurantCard>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantAdapter.RestaurantCard{
        val view = LayoutInflater.from(context).inflate(R.layout.element_restaurant,parent,false)
        return RestaurantCard(view)
    }

    override fun getItemCount(): Int {
        return arrayMenu.size
    }

    override fun onBindViewHolder(holder: RestaurantCard, position: Int) {
        val card = arrayMenu[position];
        holder.set(card, value, holder.view.context)
    }

    inner class RestaurantCard(var view : View): RecyclerView.ViewHolder(view){
        fun set(card :String, value:Int, context:Context) {
            view.menuButton.text = card;
            view.menuButton.setOnClickListener { view ->
                val intent = Intent(context, MenuActivity::class.java)
                intent.putExtra("QRScannedValue","0")
                val bundle:Bundle = intent.extras!!
                startActivity(context,intent,bundle)
            }
            view.imageView2.setImageResource(R.drawable.img_hamburgesa2);
        }
    }

}