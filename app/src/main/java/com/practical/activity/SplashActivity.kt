package com.practical.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.practical.R

class SplashActivity : AppCompatActivity() {

    var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)
        context = this

        nextScreen()
    }

    private fun nextScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(context, GenreCategoryActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 3000)
    }

}
