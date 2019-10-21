package com.appcoders.menus_everywhere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseUser


class LoginActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth;
    lateinit var callBackManager: CallbackManager;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Initialize Firebase Auth


        mAuth = FirebaseAuth.getInstance();
        callBackManager = CallbackManager.Factory.create();

        btnLoginFb.setReadPermissions("email", "public_profile")
        btnLoginFb.registerCallback(callBackManager,object:FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult) {
                handleFacebookAccesSToken(result.accessToken)
            }

            override fun onCancel() {
                Toast.makeText(baseContext,"Canceled",Toast.LENGTH_LONG).show()

            }

            override fun onError(error: FacebookException?) {
                Toast.makeText(baseContext,error.toString(),Toast.LENGTH_LONG).show()
                Log.e("ERROR", error.toString());
            }

        })


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


    private fun handleFacebookAccesSToken(accessToken: AccessToken?) {
        //Get credential
        val credential = FacebookAuthProvider.getCredential(accessToken!!.token)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = mAuth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callBackManager.onActivityResult(requestCode,resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
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
