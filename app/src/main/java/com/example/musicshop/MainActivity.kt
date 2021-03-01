package com.example.musicshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    var names = R.array.models
    var prices = R.array.prices
    var picSrc = R.array.picSrc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}

class Model (val name: String, var price: Int, var picSrc: String) {

}