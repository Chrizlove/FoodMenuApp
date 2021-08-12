package com.example.foodmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmenu.Services.DataServices
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_cart.cartItemRecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*

class CartActivity : AppCompatActivity() {
    private lateinit var bottomsheetbilladapter: BottomSheetBillAdapter
    private lateinit var emptycartadapter: EmptyCartAdapter
    private var toolbar: Toolbar? = null
    private lateinit var cartadapter: CartItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        if(DataServices.cartFoodItemList.isEmpty())
        {
            setUpEmptyCartRecyclerView()
        }
        else{
            setUpRecyclerView()
            val dialogBottomSheet = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.fragment_bottom_sheet, null)
            dialogBottomSheet.setCancelable(false)
            dialogBottomSheet.setContentView(view)
            dialogBottomSheet.show()
           // setUpBottomSheetRecyclerView()
        }
    }

    private fun setUpBottomSheetRecyclerView() {
        bottomsheetbilladapter = BottomSheetBillAdapter(this, DataServices.cartFoodItemList)
        val layoutManager =  LinearLayoutManager(this)
        val billRecyclerView = findViewById<RecyclerView>(R.id.billRecyclerView)
        billRecyclerView.layoutManager = layoutManager
        billRecyclerView.adapter = bottomsheetbilladapter
    }

    private fun setUpEmptyCartRecyclerView() {
        emptycartadapter = EmptyCartAdapter(this)
        val layoutManager =  LinearLayoutManager(this)
        cartItemRecyclerView.layoutManager= layoutManager
        cartItemRecyclerView.adapter = emptycartadapter
    }

    private fun setUpRecyclerView() {
        cartadapter = CartItemAdapter(this, DataServices.cartFoodItemList)
        val layoutManager =  LinearLayoutManager(this)
        cartItemRecyclerView.layoutManager= layoutManager
        cartItemRecyclerView.adapter = cartadapter
    }

    override fun onResume() {
        super.onResume()
    }
}