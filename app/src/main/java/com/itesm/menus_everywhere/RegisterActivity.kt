package com.itesm.menus_everywhere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase


class RegisterActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth;
    lateinit var database : FirebaseDatabase;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance()

        setContentView(R.layout.activity_register)
        registerBtn.setOnClickListener { view ->



            if (editEmailReg.text.toString().isNotEmpty() || editUsername.text.toString().isNotEmpty() ||
                  editPasswordReg.text.toString().isNotEmpty() || editConfirmPassword.text.toString().isNotEmpty()) {

                if(editPasswordReg.text.toString() == editConfirmPassword.text.toString()) {
                    if(checkBox.isChecked) {
                        mAuth.createUserWithEmailAndPassword(editEmailReg.text.toString(), editPasswordReg.text.toString())
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    val userRef = database.getReference("Usuarios/${editEmailReg.text.toString().replace('@','_').replace('.','_')}")
                                    val usuario = Usuario(editUsername.text.toString(),editEmailReg.text.toString())

                                    userRef.setValue(usuario)

                                    val user = mAuth.getCurrentUser()
                                    updateUI(user)
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(this, task.exception.toString().split(':')[1], Toast.LENGTH_LONG).show();
                                }
                            }
                    }else{
                        Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(this, "Las contraseñsa deben ser iguales", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intMin = Intent(this, mainMenu::class.java);
            startActivity(intMin);
        }
    }

}
