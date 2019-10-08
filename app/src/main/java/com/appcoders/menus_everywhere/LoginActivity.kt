package com.appcoders.menus_everywhere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginBtn.setOnClickListener { view ->
            val intMain = Intent(this, mainMenu::class.java);
            startActivity(intMain);
            finish();
        }
        registerButton.setOnClickListener { view ->
            val intRegister = Intent(this, RegisterActivity::class.java);
            startActivity(intRegister);
        }
    }
}
