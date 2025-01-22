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

class CatalogActivity : AppCompatActivity() {

    private val productCatalogList = mutableListOf<Product>()
    private val productCartList = mutableListOf<Product>()

    private lateinit var catalogToolbar: Toolbar
    private lateinit var catalogRV: RecyclerView
    private lateinit var catalogFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        catalogToolbar = findViewById(R.id.catalogToolbar)
        catalogRV = findViewById(R.id.catalogRV)
        catalogFAB = findViewById(R.id.catalogFAB)
        val roleInAnim = AnimationUtils.loadAnimation(this, R.anim.role_in)
        val roleOutAnim = AnimationUtils.loadAnimation(this, R.anim.role_out)

        setSupportActionBar(catalogToolbar)
        title = getString(R.string.toolbar_title)

        productCatalogList.addAll(Product.products)
        val adapter = AdapterRV(this, productCatalogList)
        catalogRV.layoutManager = LinearLayoutManager(this)
        catalogRV.adapter = adapter

        adapter.setOnProductClickListener { product: Product ->
            AlertDialog.Builder(this)
                .setPositiveButton("В корзину") { _, _ ->
                    val index = productCatalogList.indexOf(product)
                    productCartList.add(product)
                    productCatalogList.remove(product)
                    adapter.notifyItemRemoved(index)
                }
                .setNegativeButton("Отмена") { _, _ -> }
                .show()
        }

        catalogFAB.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("products", productCartList.toTypedArray())
            catalogFAB.startAnimation(roleOutAnim)
            startActivity(intent)
            finish()
        }

        catalogFAB.startAnimation(roleInAnim)
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