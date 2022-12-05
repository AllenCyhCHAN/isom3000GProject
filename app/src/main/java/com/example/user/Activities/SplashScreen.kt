package com.example.user.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.user.Models.DataDriver
import com.example.user.R
import java.io.File


class SplashScreen : AppCompatActivity() {
    private lateinit var masterDataDriver: DataDriver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
 
         masterDataDriver = DataDriver(filesDir)
        //check go to create user page or sign in page
        val logined = masterDataDriver.getAcIsCreated()
        var temp:Intent;

        if (logined){
              temp = Intent(this@SplashScreen, LoginActivity::class.java)

        } else {
              temp = Intent(this@SplashScreen, RegisterActivity::class.java)

        }

        Handler().postDelayed({
            temp.putExtra("driver",masterDataDriver)
            startActivity(temp)
            finish()
        }, 1000)
    }
}