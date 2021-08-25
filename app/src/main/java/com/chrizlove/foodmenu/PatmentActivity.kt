package com.chrizlove.foodmenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_patment.*

class PatmentActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patment)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        free.setOnClickListener {
            val intent = Intent(this, OrderSuccessActivity::class.java)
            startActivity(intent)
        }
    }
}