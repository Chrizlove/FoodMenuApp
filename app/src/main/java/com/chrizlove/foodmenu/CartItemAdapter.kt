package com.chrizlove.foodmenu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chrizlove.foodmenu.Model.FoodItem
import com.chrizlove.foodmenu.Services.DataServices

class CartItemAdapter(val context: Context,val cartItemsList: List<FoodItem>): RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder> (){
    private lateinit var bottomsheetbilladapter: BottomSheetBillAdapter
    inner class CartItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cartItemImage = itemView?.findViewById<ImageView>(R.id.cartItemImage)
        val cartItemName = itemView?.findViewById<TextView>(R.id.bottomSheetCardName)
       // val cartItemPrice = itemView?.findViewById<TextView>(R.id.foodItemPriceTextView)
        val quantity = itemView?.findViewById<TextView>(R.id.quantityValue)
        val quantityminusButton = itemView?.findViewById<TextView>(R.id.quantityminusButton)
        val quantityplusButton = itemView?.findViewById<TextView>(R.id.quantityplusButton)

        fun bindFoodItem(foodItem: FoodItem, context: Context)
        {
            val resourceId = context.resources.getIdentifier(foodItem.image,"drawable", context.packageName)
            cartItemImage?.setImageResource(resourceId)
            cartItemName?.text = foodItem.name
            quantity?.text=foodItem.quantity.toString()
            quantityminusButton.setOnClickListener{
              if(foodItem.quantity>1)
              {
                  foodItem.quantity-=1
                  quantity?.text = foodItem.quantity.toString()

              }
                else if(foodItem.quantity==1)
              {
                //implement method to remove fooditem and remove viewholder
              }
            }
            quantityplusButton.setOnClickListener{
                if(foodItem.quantity>=6)
                {
                    Toast.makeText(context,"Limit Exceeded!",Toast.LENGTH_LONG).show()
                }
                else{
                    foodItem.quantity+=1
                    quantity?.text = foodItem.quantity.toString()
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.cart_item_card, parent, false)
            return CartItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder?.bindFoodItem(cartItemsList[position], context)
    }

    override fun getItemCount(): Int {
        return cartItemsList.count()
    }


}

