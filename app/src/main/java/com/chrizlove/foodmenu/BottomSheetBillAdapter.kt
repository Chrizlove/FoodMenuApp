package com.chrizlove.foodmenu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chrizlove.foodmenu.Model.FoodItem
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions


class BottomSheetBillAdapter(val context: Context, val cartItemsList: ArrayList<FoodItem>): RecyclerView.Adapter<BottomSheetBillAdapter.BottomSheetBillViewHolder> () {
    inner class BottomSheetBillViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val bottomsheetcardname = itemView?.findViewById<TextView>(R.id.bottomSheetCardName)
        val bottomsheetcardquantity = itemView?.findViewById<TextView>(R.id.bottomSheetCardQuantityValue)
        val bottomsheetcardtotal = itemView?.findViewById<TextView>(R.id.bottomSheetCardTotalAmount)
        fun bindBillItem(foodItem: FoodItem, context: Context)
        {
            bottomsheetcardname?.text = foodItem.name
            bottomsheetcardquantity?.text= "x${foodItem.quantity}"
            val totalvalue = (foodItem.quantity)*(foodItem.price.toInt())
            bottomsheetcardtotal?.text="Total- Rs. ${totalvalue}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetBillViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_bill_card, parent, false)
        return BottomSheetBillViewHolder(view)
    }

    override fun onBindViewHolder(holder: BottomSheetBillViewHolder, position: Int) {
        holder?.bindBillItem(cartItemsList[position], context)
    }

    override fun getItemCount(): Int {
        return cartItemsList.count()
    }
    public fun updateBill(newCartList: ArrayList<FoodItem>)
    {
        cartItemsList.clear()
        cartItemsList.addAll(newCartList)
        notifyDataSetChanged()
    }
}
