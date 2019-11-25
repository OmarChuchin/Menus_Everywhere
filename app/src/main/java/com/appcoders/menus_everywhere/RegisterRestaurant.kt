package com.appcoders.menus_everywhere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_register_restaurant.*
import org.json.JSONObject

class RegisterRestaurant : AppCompatActivity() {

    private var areFieldsComplete = false
    lateinit var database : FirebaseDatabase
    private val TAG = "SubmitInfo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_restaurant)
        database = FirebaseDatabase.getInstance()

        btnSubmitRestaurantInfo.setOnClickListener { view ->
            Log.d(TAG,"Button Pressed")
            collectInfo()
            if(this.areFieldsComplete){
                Log.d("SubmitInfo","Data read correctly")
                this.getRestaurantID()
//                Toast.makeText(this,"Your info has been sent",Toast.LENGTH_SHORT).show()
            }else{
                if(!this.samePasswords(etPassword.text.toString(),etConfirmPassword.text.toString())){
                    Toast.makeText(this,"The passwords do not coincide, please try again",Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(
                        this,
                        "All of the fields must be completed to continue",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun collectInfo(){
        val address = etAddress.text.toString()
        val password = etPassword.text.toString()
        val confirmPassword = etConfirmPassword.text.toString()
        val adminEmail = etAdminEmail.text.toString()
        val contEmail = etContactEmail.text.toString()
        val resName = etRestaurantName.text.toString()
        this.areFieldsComplete = address.isNotEmpty() && resName.isNotEmpty() && this.validEmail(adminEmail) && this.validEmail(contEmail) && this.samePasswords(password,confirmPassword)
    }

    private fun validEmail(string: String):Boolean{
        return "@" in string && ".com" in string
    }

    private fun samePasswords(string: String,string2: String):Boolean{
        return (string.length>0 && string2.length>0 && string==string2)
    }

    private fun registerRestaurant(restauranteID: Long){
        val address = etAddress.text.toString()
//        val password = etPassword.text.toString()
//        val confirmPassword = etConfirmPassword.text.toString()
        val adminEmail = etAdminEmail.text.toString()
        val contEmail = etContactEmail.text.toString()
        val resName = etRestaurantName.text.toString()
        val restaurantRef = database.getReference("Restaurante/${restauranteID}")
        val info = Información(adminEmail,contEmail,address)
        val rInfo = RestauranteInfo(resName,info)
        restaurantRef.setValue(rInfo)
//        restaurantRef.setValue("Nombre:"+resName)
        Toast.makeText(this,"The restaurant ${resName} has been added to the database",Toast.LENGTH_SHORT).show()
        val i = Intent(baseContext,mainMenu::class.java)
        startActivity(i)
    }

    private fun getRestaurantID(){
        var id : Long
        database.getReference("Restaurante").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(baseContext,"Please connect to the internet to continue",Toast.LENGTH_SHORT).show()
                Log.d(TAG,"There has been an error when reading from the database,${p0}")
            }

            override fun onDataChange(p0: DataSnapshot) {
                var previouslyExists = false
                var inputName = etRestaurantName.text.toString()
                inputName = inputName.toLowerCase()
                var position : Long = 0
                for(registro in p0.children){
                    val topKey = registro.key!!.toLong()
                    Log.d(TAG,topKey.toString())
                    var name = registro.child("nombre").value.toString()
                    if(name == null || name == "" || name =="null"){
                        name = registro.child("nombre").value.toString()
                    }
                    if(position<topKey){
                        position=topKey
                    }
                    name = name.toLowerCase()
//                    Log.d(TAG,inputName+" Input Name")
//                    Log.d(TAG,name + " Database name")
                    if(inputName == name){
                        Log.d(TAG,"The name is already used")
                        previouslyExists = true
                        break
                    }
                }
                if(!previouslyExists){
                    id = position + 1
                    Log.d(TAG,"Updating the database now")
                    registerRestaurant(id)
                } else{
                    Toast.makeText(baseContext,"This Restaurant is already registered on the App, sorry :)",Toast.LENGTH_SHORT).show()
                    Log.d(TAG,"This restaurant is previously used")
                }
            }

        })
    }
}

data class Información(var admin:String,var contacto:String,var dirección:String){
    constructor() : this("","","")
}
data class RestauranteInfo(var nombre:String,val Información:Información){
    constructor() : this("", Información())
}
