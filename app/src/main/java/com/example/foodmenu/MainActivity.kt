package com.example.foodmenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmenu.Model.FoodItem
import com.example.foodmenu.Services.DataServices
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.foodcard.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: FoodItemAdapter
    private var toolbar: Toolbar? = null
    public var cartItemsCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        adapter = FoodItemAdapter(this,DataServices.foodItemsList)
        val layoutManager = GridLayoutManager(this, 2)
        foodItemRecyclerview.layoutManager= layoutManager
        foodItemRecyclerview.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
        return true
    }
}