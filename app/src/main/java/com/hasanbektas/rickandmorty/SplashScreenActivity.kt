package com.hasanbektas.rickandmorty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.hasanbektas.rickandmorty.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    private val GİF_URL = "https://media1.giphy.com/media/gJ2TzwqdRoKoZ0KWhW/giphy.gif?cid=22ed0014hgyi8oc5sml01g9qil6qp0lrleye0n8670mrqhw3&rid=giphy.gif&ct=s"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)

        Glide.with(this).load(GİF_URL)
            .into(binding.splashScrenGiff)
    }
}