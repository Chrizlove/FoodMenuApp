package com.chrizlove.foodmenu

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class EmptyCartAdapter(val context: Context): RecyclerView.Adapter<EmptyCartAdapter.EmptyCartViewHolder>() {
    inner class EmptyCartViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val returntomenubutton = itemView?.findViewById<Button>(R.id.returnToMenuButton2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmptyCartViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.empty_cart_card, parent, false)
        return EmptyCartViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmptyCartViewHolder, position: Int) {
        holder?.returntomenubutton.setOnClickListener{
            val intent = Intent(context, MainActivity::class.java)
            startActivity(context,intent, null)
        }
    }

    override fun getItemCount(): Int {
        return 1
    }
}