package com.example.urban_module15_animation2

import android.os.Parcel
import android.os.Parcelable

class Product(val picture: Int, val name: String, val price: String): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(picture)
        parcel.writeString(name)
        parcel.writeString(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        val products = listOf(
            Product(R.drawable.carrot, "Морковь", "15.0"),
            Product(R.drawable.lemon, "Лимон", "32.0"),
            Product(R.drawable.pear, "Груша", "25.0"),
            Product(R.drawable.pineapple, "Ананас", "78.0"),
            Product(R.drawable.red_apple, "Красное яблоко", "21.0"),
            Product(R.drawable.tomato, "Помидор", "45.0"),
            Product(R.drawable.watermelon, "Арбуз", "115.0")
        )

        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}