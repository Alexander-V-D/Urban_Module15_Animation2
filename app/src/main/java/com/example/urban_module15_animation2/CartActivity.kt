package com.example.urban_module15_animation2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CartActivity : AppCompatActivity() {

    private lateinit var cartToolbar: Toolbar
    private lateinit var cartRV: RecyclerView
    private lateinit var cartFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartToolbar = findViewById(R.id.cartToolbar)
        cartRV = findViewById(R.id.cartRV)
        cartFAB = findViewById(R.id.cartFAB)
        val roleInAnim = AnimationUtils.loadAnimation(this, R.anim.role_in)
        val roleOutAnim = AnimationUtils.loadAnimation(this, R.anim.role_out)

        setSupportActionBar(cartToolbar)
        title = getString(R.string.toolbar_title)

        val productCartList = mutableListOf<Product>()
        intent.getParcelableArrayExtra("products")?.forEach {
            productCartList.add(it as Product)
        }
        val adapter = AdapterRV(this, productCartList)
        cartRV.layoutManager = LinearLayoutManager(this)
        cartRV.adapter = adapter

        adapter.setOnProductClickListener { product: Product ->
            AlertDialog.Builder(this)
                .setPositiveButton("Удалить из корзины") { _, _ ->
                    val index = productCartList.indexOf(product)
                    productCartList.remove(product)
                    adapter.notifyItemRemoved(index)
                }
                .setNegativeButton("Отмена") { _, _ -> }
                .show()
        }

        cartFAB.setOnClickListener {
            val intent = Intent(this, ReceiptActivity::class.java)
            intent.putExtra("products", productCartList.toTypedArray())
            cartFAB.startAnimation(roleOutAnim)
            startActivity(intent)
            finish()
        }

        cartFAB.startAnimation(roleInAnim)
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