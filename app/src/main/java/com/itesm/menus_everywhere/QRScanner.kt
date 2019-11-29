package com.itesm.menus_everywhere

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.app.ActivityCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import java.io.IOException

//Clase para crear el scanner de QR. Inicializa la cámara, el scanner y procesa los códigos.

class QRScanner : AppCompatActivity() {

    private var cameraSource: CameraSource? = null
    private var cameraView: SurfaceView? = null
    private var token = ""
    private var tokenanterior = ""
    private val MY_PERMISSIONS_REQUEST_CAMERA = 1

    private val QRINTENTTAG = R.string.QValueTag.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        // remove title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)

        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        this.setContentView(R.layout.activity_qrscanner)

        //Set default values to null or it's equivalent
        this.token = ""
        this.tokenanterior = ""

        this.cameraView = findViewById<View>(R.id.cameraView) as SurfaceView?
        this.initQR()

    }

    private fun initQR() {

        // creo el detector qr
        val barcodeDetector = BarcodeDetector.Builder(this)
            .setBarcodeFormats(Barcode.QR_CODE)
            .build()

        // creo la camara
        this.cameraSource = CameraSource.Builder(this, barcodeDetector)
            .setRequestedPreviewSize(1600, 1024)
            .setAutoFocusEnabled(true) //you should add this feature
            .build()

        // listener de ciclo de vida de la camara
        this.cameraView!!.holder.addCallback(object : SurfaceHolder.Callback {

            override fun surfaceCreated(holder: SurfaceHolder) {

//                pedirPermisos()
                if (ActivityCompat.checkSelfPermission(baseContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        // verificamos la version de Android que sea al menos la M para mostrar
                        // el dialog de la solicitud de la camara
                        if (shouldShowRequestPermissionRationale(
                                Manifest.permission.CAMERA))
                        ;
                        requestPermissions(arrayOf(Manifest.permission.CAMERA),
                            MY_PERMISSIONS_REQUEST_CAMERA)
                    }
//                    startActivity(somethingWentWrong)
                } else {
                    try {
                        cameraSource!!.start(cameraView!!.holder)
                    } catch (ie: IOException) {
                        Log.e("CAMERA SOURCE", "There has been an error")
//                        startActivity(somethingWentWrong)
                    }

                }

            }
            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource!!.stop()
            }

        })

        // preparo el detector de QR
        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {

            override fun release() {}

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                val barcodes = detections.getDetectedItems()

                if (barcodes.size() > 0) {

                    // obtenemos el token
                    token = barcodes.valueAt(0).displayValue.toString()

                    // verificamos que el token anterior no sea igual al actual
                    // esto es util para evitar multiples llamadas empleando el mismo token
                    if (token != tokenanterior) {

                        // guardamos el ultimo token proceado
                        tokenanterior = token
                        Log.i("token", token)


                        Thread(object : Runnable {
                            override fun run() {
                                try {
                                    synchronized(this) {
                                        Thread.sleep(5000)
                                        // limpiamos el token
                                        tokenanterior = ""
                                    }
                                } catch (e: InterruptedException) {
                                    Log.e("Error", "Waiting didnt work!!")
                                    e.printStackTrace()
                                }
                            }
                        }).start()

                        tokenDetected(token)


                    }
                }
            }
        })

    }

    private fun tokenDetected(token: String){
        //Aqui va la funcion que dicta que debe hacer el scanner QR post scaneo.
        Log.d("QRDetection",token)
        val intent = Intent(this,MenuActivity::class.java)
        intent.putExtra("QRScannedValue",token)
        startActivity(intent)

    }



    override fun onDestroy() {
        super.onDestroy()
        this.cameraSource?.stop()
        this.cameraView = null
        this.cameraSource = null
    }


}
