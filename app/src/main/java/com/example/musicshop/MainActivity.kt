package com.example.musicshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    var names = R.array.models
    var prices = R.array.prices
    var picSrc = R.array.picSrc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun SelectItem(view: View) {
        var imageView = findViewById<ImageView>(R.id.imageView4)

        imageView.setImageResource(R.drawable.fender_stratocaster)
    }
}

class Model (val name: String, var price: Int, var picSrc: String) {

}