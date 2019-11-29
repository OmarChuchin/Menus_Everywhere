package com.itesm.menus_everywhere

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.element_menu.view.*

class RestaurantAdapter (val context: Context, val arrayMenu: Array<String>, val arrvalue: Array<String>, val arrImage: Array<Int>): RecyclerView.Adapter<RestaurantAdapter.RestaurantCard>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantAdapter.RestaurantCard{
        val view = LayoutInflater.from(context).inflate(R.layout.element_restaurant,parent,false)
        return RestaurantCard(view)
    }

    override fun getItemCount(): Int {
        return arrayMenu.size
    }

    override fun onBindViewHolder(holder: RestaurantCard, position: Int) {
        val card = arrayMenu[position];
        val value = arrvalue[position];
        var image:Int;
        try {
            image = arrImage[position];
        }catch (e: ArrayIndexOutOfBoundsException){
            image = R.drawable.food_holder
        }
        holder.set(card, value, holder.view.context, image)
    }

    inner class RestaurantCard(var view : View): RecyclerView.ViewHolder(view){
        fun set(card :String, value:String, context:Context, image: Int) {
            view.menuButton.text = card;
            view.menuButton.setOnClickListener { view ->
                val intent = Intent(context, MenuActivity::class.java)
                intent.putExtra("QRScannedValue",value)
                val bundle:Bundle = intent.extras!!
                startActivity(context,intent,bundle)
            }
            view.imageView2.setImageResource(image);
        }
    }

}