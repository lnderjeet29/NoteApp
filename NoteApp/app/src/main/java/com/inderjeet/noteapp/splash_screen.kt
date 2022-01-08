package com.inderjeet.noteapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class splash_screen : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        val thread: Unit = object : Thread() {
            override fun run() {
                try {
                    sleep(2000)
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    val intent = Intent(this@splash_screen, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }
}