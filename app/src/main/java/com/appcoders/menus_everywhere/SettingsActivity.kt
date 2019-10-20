package com.appcoders.menus_everywhere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        settingsLogoutBtn.setOnClickListener { view ->
            FirebaseAuth.getInstance().signOut();
            LoginManager.getInstance().logOut();
            val loginActivity = Intent(this, LoginActivity::class.java);
            startActivity(loginActivity);
            finish()

        }

    }

}
