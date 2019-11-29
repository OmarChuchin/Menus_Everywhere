package com.itesm.menus_everywhere.ui.scan

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.itesm.menus_everywhere.QRScanner
import com.itesm.menus_everywhere.R

class ScanFragment : Fragment() {

    private val MY_PERMISSIONS_REQUEST_CAMERA = 1
    private var secondComing = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_scan, container, false)
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Prueba si tiene el permiso
        if (ContextCompat.checkSelfPermission(layoutInflater.context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            // No lo tiene, solicitarlo
            requestPermissions(arrayOf(Manifest.permission.CAMERA), this.MY_PERMISSIONS_REQUEST_CAMERA)
        } else {
//            val intent = I
//                Toast.makeText(this,"ya tengo permisos perro",Toast.LENGTH_SHORT).show()
            val callScanner = Intent(layoutInflater.context, QRScanner::class.java)
            startActivity(callScanner)
        }

    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode==this.MY_PERMISSIONS_REQUEST_CAMERA && grantResults.size>0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Otorgó el permiso
                if (ContextCompat.checkSelfPermission(layoutInflater.context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    val callScanner = Intent(layoutInflater.context,QRScanner::class.java)
                    startActivity(callScanner)
                }
            } else {
                // Mostrar mensaje sobre cómo activar el permiso
            }
        }
    }

    override fun onResume() {
        super.onResume()
//        this.controler(layoutInflater,container)
        this.controler()
    }

    private fun controler() {
        if(this.secondComing == 0){
            this.secondComing++;
            Log.d("QRBack","He salido del framento original")
        }
        else{
            Log.d("QRBack", "He vuelto al fragmento original")
//            super.setTargetFragment(this.targetFragment,R.layout.fragment_home)
            super.getActivity()!!.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("QRDestroy","I was destroyed")
        this.secondComing = 0
    }
}