package com.chrizlove.foodmenu.Services

import android.location.Address
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chrizlove.foodmenu.Model.FoodItem
import com.chrizlove.foodmenu.Model.UserAddress

object DataServices {

    val foodItemsList = listOf(
        FoodItem("burger", "Burger","30",1),
        FoodItem("pasta", "Pasta","80",1),
        FoodItem("fries", "French Fries","50",1),
        FoodItem("cola", "Coca Cola","40",1),
        FoodItem("icecream", "Ice Cream","60",1),
        FoodItem("pizza", "Pizza","130",1)
    )
    var cartFoodItemList =  arrayListOf<FoodItem>()
    var address = listOf<Address>()
    var userInputAddress= arrayListOf<UserAddress>(
    )
}
