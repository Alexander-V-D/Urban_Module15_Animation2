package com.example.urban_module15_animation2

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val startCL: ConstraintLayout = findViewById(R.id.startCL)
        val shopEnterBTN: Button = findViewById(R.id.shopEnterBTN)

        val appearAnim = AnimationUtils.loadAnimation(this, R.anim.move_from_left_to_center)
        startCL.startAnimation(appearAnim)

        shopEnterBTN.setOnClickListener {
            startActivity(Intent(this, CatalogActivity::class.java))
            finish()
        }
    }
}