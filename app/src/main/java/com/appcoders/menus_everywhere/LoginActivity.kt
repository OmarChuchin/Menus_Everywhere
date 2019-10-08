package com.appcoders.menus_everywhere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser


class LoginActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener { view ->
            if(editPasswordReg.text.toString().isNotEmpty() || editPasswordReg.text.toString().isNotEmpty()) {

                val email = editEmailReg.text.toString();
                val password = editPasswordReg.text.toString();

                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = mAuth.currentUser
                            Toast.makeText(this,"Bienvenido", Toast.LENGTH_LONG).show();
                            updateUI(user)
                        } else {
                            Toast.makeText(this,task.exception.toString().split(':')[1], Toast.LENGTH_LONG).show();
                        }

                    }
            }else{
                Toast.makeText(this,"Debes llenar los campos", Toast.LENGTH_LONG).show();
            }
        }
        registerButton.setOnClickListener { view ->
            val intRegister = Intent(this, RegisterActivity::class.java);
            startActivity(intRegister);
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intMain = Intent(this, mainMenu::class.java);
            startActivity(intMain);
            finish();
        }
    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }
}
