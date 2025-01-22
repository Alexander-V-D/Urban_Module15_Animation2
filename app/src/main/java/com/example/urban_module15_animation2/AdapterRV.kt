package com.example.urban_module15_animation2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdapterRV(private val context: Context, private val productList: List<Product>) :
    RecyclerView.Adapter<AdapterRV.ProductViewHolder>() {

    private var onProductClickListener: OnProductClickListener? = null

    interface OnProductClickListener {
        fun onProductClick(product: Product)
    }

    class ProductViewHolder(itemView: View) : ViewHolder(itemView) {
        val itemPictureIV: ImageView = itemView.findViewById(R.id.itemPictureIV)
        val itemNameTV: TextView = itemView.findViewById(R.id.itemNameTV)
        val itemPriceTV: TextView = itemView.findViewById(R.id.itemPriceTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater
            .from(parent.context).inflate(R.layout.list_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.itemPictureIV.setImageResource(product.picture)
        holder.itemNameTV.text = product.name
        holder.itemPriceTV.text = product.price
        if (onProductClickListener != null) {
            holder.itemView.setOnClickListener {
                onProductClickListener!!.onProductClick(product)
            }
        }
        val animation = AnimationUtils.loadAnimation(context, R.anim.move_from_left_to_center)
            .apply {
                duration = 300L * position
            }
        holder.itemView.startAnimation(animation)
    }

    fun setOnProductClickListener(listener: (Product) -> Unit) {
        onProductClickListener = object : OnProductClickListener {
            override fun onProductClick(product: Product) {
                listener(product)
            }

        }
    }
}