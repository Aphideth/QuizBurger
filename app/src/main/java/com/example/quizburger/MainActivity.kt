package com.example.quizburger

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import boofcv.alg.fiducial.qrcode.QrCodeEncoder
import boofcv.alg.fiducial.qrcode.QrCodeGeneratorImage
import boofcv.android.ConvertBitmap

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun displayQuiz(view: View){
        val vueQuiz = Intent(this, QuizActivity::class.java)
        startActivity(vueQuiz)
    }

    override fun onResume() {
        super.onResume()
        imageQr.setImageBitmap(null)
    }

    fun displayScores(view: View){
        val save = getSharedPreferences("com.example.quizburger", Context.MODE_PRIVATE)
        val score = save.getInt("myScores", 0)
        if(score in 4..5){
            Toast.makeText(this, "Bravo ! Tu as gagné un burger de ton choix : Présente ton QrCode en caisse !", Toast.LENGTH_SHORT).show()
            imageQr.setImageBitmap( generateQrCode ("EN-BURGER-FREE-4541237884512235"))
        }
        if(score in 2..3){
            Toast.makeText(this, "Bravo ! Tu as gagné un soda de ton choix : Présente ton QrCode en caisse !", Toast.LENGTH_SHORT).show()
            imageQr.setImageBitmap( generateQrCode ("EN-BURGER-FREE-4541278412003659"))
        }
        if(score in 0..1){
            Toast.makeText(this, "Désolé, tu n'asps de gain", Toast.LENGTH_SHORT).show()
            imageQr.setImageBitmap(null)
        }
    }

    fun generateQrCode (message : String): Bitmap{
        val qr = QrCodeEncoder().addAutomatic(message).fixate()
        val render = QrCodeGeneratorImage(20)
        val qrRender = render.render(qr)
        val bitmap = ConvertBitmap.grayToBitmap(qrRender.gray, Bitmap.Config.ARGB_8888);
        return bitmap
    }
}