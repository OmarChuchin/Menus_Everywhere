package com.appcoders.menus_everywhere

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        Toast.makeText(this,"Welcome to our app",Toast.LENGTH_LONG).show()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        btnAccessCamera.setOnClickListener {
            val changeToCameraIntent = Intent(this,QRScanner::class.java)
            startActivity(changeToCameraIntent)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> this.actionBarItemSelected(1)

            R.id.action_user -> this.actionBarItemSelected(2)
            else -> this.actionBarItemSelected(0)
        }
        return when (item.itemId) {
            R.id.action_settings -> true

            R.id.action_user -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun actionBarItemSelected(i: Int) {
        var newActivityIntent : Intent? = null
        var itemClicked = i
        when(itemClicked){
            2 -> {
                newActivityIntent = Intent(this,ProfileActivity::class.java)
            }
            else -> itemClicked=0
        }
        if(itemClicked>0 && newActivityIntent!=null) {
            startActivity(newActivityIntent)
        }
    }
}
