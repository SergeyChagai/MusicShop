package com.example.musicshop

import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
   // private var currentModel = Model("FENDER SQUIER MM STRATOCASTER HARD TAIL BLACK", 12400, getDrawable(R.drawable.fender_stratocaster) as Drawable)
    private var modelsList = arrayListOf<Model>()
    private var modelsNames = arrayListOf<String>()
    private var basket = arrayListOf<BasketItem>()
    private lateinit var currentModel: Model


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var reciever = BuyMessageReceiver()
        registerReceiver(reciever, IntentFilter("ru.music_shop.action.BROADCAST_BUY_MESSAGE"))

        var spinner = findViewById<Spinner>(R.id.spinner)
        modelsList.add(Model("FENDER SQUIER MM STRATOCASTER HARD TAIL BLACK", 12400, getDrawable(R.drawable.fender_stratocaster) as Drawable))
        modelsList.add(Model("GIBSON Les Paul", 23400, getDrawable(R.drawable.epiphone_les_paul) as Drawable))
        modelsList.add(Model("IBANEZ RGRT421-WNF", 32600, getDrawable(R.drawable.ibanez_rgrt421_wnf) as Drawable))
        modelsList.add(Model("Yamaha C40", 9600, getDrawable(R.drawable.yamaha_c40) as Drawable))
        modelsList.add(Model("EPIPHONE ES-339 Cherry", 61000, getDrawable(R.drawable.epiphone_es330_cherry) as Drawable))
        modelsList.forEach{
            modelsNames.add(it.name)
        }

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, modelsNames )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var textView = p1 as TextView
                currentModel = modelsList.first{it.name == textView.text}

                var image = findViewById<ImageView>(R.id.imageView4)
                image.setImageDrawable(currentModel.picture)
                var modelNameView = findViewById<TextView>(R.id.textView)
                modelNameView.text = textView.text
                var priceView = findViewById<TextView>(R.id.textView3)
                priceView.text = currentModel.price.toString() + " RUB"
            }
        }
    }

    public fun CountDecrease(view: View){
        var count = findViewById<TextView>(R.id.count)
        var totalPrice = findViewById<TextView>(R.id.total_price_view)
        var num = count.text.toString().toInt()
        if (num == 0)
            return
        else
            num--
        count.text = num.toString()
        totalPrice.text = (currentModel.price*num).toString() + " RUB"
    }
    public fun CountIncrease(view: View){
        var count = findViewById<TextView>(R.id.count)
        var totalPrice = findViewById<TextView>(R.id.total_price_view)
        var num = count.text.toString().toInt()
        num++
        count.text = num.toString()
        totalPrice.text = (currentModel.price*num).toString() + " RUB"
    }
    public fun AddToBasketButtonClick(view: View) {
        var countView = findViewById<TextView>(R.id.count)
        var count = countView.text.toString().toInt()
        var totalPrice = currentModel.price*count
        var basketItem = BasketItem(currentModel, count, totalPrice)
        if (count == 0 || totalPrice == 0)
            return
        basket.add(basketItem)
        var toast = Toast.makeText(this, "Item was add successful", Toast.LENGTH_SHORT)
        toast.show()

        var totalPriceView = findViewById<TextView>(R.id.total_price_view)
        countView.text = "0"
        totalPriceView.text = ""
    }
    public fun OnBasketButtonClick(view: View){
        var names = arrayListOf<String>()
        var counts = arrayListOf<Int>()
        var totals = arrayListOf<Int>()
        basket.forEach{
            names.add(it.model.name)
            counts.add(it.count)
            totals.add(it.totalPrice)
        }
        var intent = Intent(this, BascetActivity::class.java)
        intent.putExtra("names", names)
        intent.putExtra("counts", counts)
        intent.putExtra("totals", totals)
        startActivity(intent)
    }
}

class Model (val name: String, var price: Int, var picture: Drawable ) {

}
class BasketItem(val model: Model, var count: Int, var totalPrice: Int){

}