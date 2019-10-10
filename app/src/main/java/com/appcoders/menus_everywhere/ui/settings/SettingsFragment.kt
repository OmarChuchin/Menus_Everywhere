package com.appcoders.menus_everywhere.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.appcoders.menus_everywhere.LoginActivity
import com.appcoders.menus_everywhere.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_setting.view.*

class SettingsFragment : Fragment() {
    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_setting, container, false)
        val button = root.logutButton;
        button.setOnClickListener { view ->
            signOut()
            val int = Intent(inflater.context, LoginActivity::class.java)
            startActivity(int)
        }

        return root
    }

    fun signOut(){
        fbAuth.signOut()

    }
}