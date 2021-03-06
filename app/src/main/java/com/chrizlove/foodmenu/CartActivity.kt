package com.chrizlove.foodmenu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chrizlove.foodmenu.Model.FoodItem
import com.chrizlove.foodmenu.Services.DataServices
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cart_item_card.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet.view.*


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
            BottomSheetBehavior.from(bottom_sheet).apply {
                peekHeight=0
            }
        }
        else{
            setUpBottomSheetRecyclerView()
            setUpRecyclerView()
            BottomSheetBehavior.from(bottom_sheet).apply {
                bottom_sheet.viewTreeObserver.addOnGlobalLayoutListener(
                    object: ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        bottom_sheet.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        peekHeight = orderBill.top
                    }
                })
            }
            bottom_sheet.payButton.setOnClickListener{
                val intent = Intent(this, MapsActivity::class.java)
                startActivity(intent)
            }

        }

        //Updating total amount value
        var totalTextString=0
        for(billitem in DataServices.cartFoodItemList)
        {
            totalTextString+=((billitem.quantity)*(billitem.price.toInt()))
        }
        totalText.text = "Total Payable Amount : ${totalTextString.toString()}"

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
   //private fun getCartItemsList(): LiveData<ArrayList<FoodItem>> {
       // return DataServices.cartFoodItemList
    //}
}

