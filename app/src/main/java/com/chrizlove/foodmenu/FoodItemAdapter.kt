package com.chrizlove.foodmenu

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chrizlove.foodmenu.Model.FoodItem
import com.chrizlove.foodmenu.Services.DataServices.cartFoodItemList

class FoodItemAdapter(val context: Context,val foodItemsList: List<FoodItem>): RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder> (){
    inner class FoodItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var addedToCart= false
        val foodItemImage = itemView?.findViewById<ImageView>(R.id.couponImage)
        val foodItemName = itemView?.findViewById<TextView>(R.id.foodItemNameTextView)
        val foodItemPrice = itemView?.findViewById<TextView>(R.id.foodItemPriceTextView)
        val addToCartButton = itemView?.findViewById<Button>(R.id.returnToMenuButton)

        fun bindFoodItem(foodItem: FoodItem, context: Context,position: Int)
        {
        val resourceId = context.resources.getIdentifier(foodItem.image,"drawable", context.packageName)
            foodItemImage?.setImageResource(resourceId)
            foodItemName?.text = foodItem.name
            foodItemPrice?.text = "Rs . ${foodItem.price}"
            addToCartButton?.setOnClickListener{
                if(addedToCart==false)
                {
                    foodItem.quantity=1
                    cartFoodItemList.add(foodItem)
                   // FoodItem(resourceId.toString(),foodItem.name,foodItem.price,1)
                    addToCartButton?.text="Remove From Cart"
                    addToCartButton?.setBackgroundResource(R.drawable.add_cart_button_red)
                    addedToCart=true
                }
                else {
                    cartFoodItemList.remove(foodItem)
                    addToCartButton?.text="Add To Cart"
                    addToCartButton?.setBackgroundResource(R.drawable.add_cart_button_green)
                    addedToCart=false
                }
                for(foodItem in cartFoodItemList)
                {
                    Log.d("FoodItemInCart",foodItem.name)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.foodcard, parent, false)
        return FoodItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        holder?.bindFoodItem(foodItemsList[position], context,position)
    }

    override fun getItemCount(): Int {
        return foodItemsList.count()
    }
}


