package com.example.urban_module15_animation2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ReceiptActivity : AppCompatActivity() {

    private lateinit var receiptToolbar: Toolbar
    private lateinit var receiptNamesTV: TextView
    private lateinit var receiptPricesTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt)

        receiptToolbar = findViewById(R.id.receiptToolbar)
        receiptNamesTV = findViewById(R.id.receiptNamesTV)
        receiptPricesTV = findViewById(R.id.receiptPricesTV)

        setSupportActionBar(receiptToolbar)
        title = getString(R.string.toolbar_title)

        val boughtProducts = mutableListOf<Product>()
        intent.getParcelableArrayExtra("products")?.forEach {
            boughtProducts.add(it as Product)
        }
        var sum = 0.0
        boughtProducts.forEach {
            receiptNamesTV.append("${it.name}\n")
            receiptPricesTV.append("${it.price}\n")
            sum += it.price.toDouble()
        }
        receiptNamesTV.append("ИТОГО")
        receiptPricesTV.append("$sum")

        val animation = AnimationUtils.loadAnimation(this, R.anim.fade)
        receiptNamesTV.startAnimation(animation)
        receiptPricesTV.startAnimation(animation)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuExitBTN -> finishAffinity()
        }
        return true
    }
}