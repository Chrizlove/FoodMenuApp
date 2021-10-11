package com.chrizlove.foodmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chrizlove.foodmenu.Services.DataServices
import kotlinx.android.synthetic.main.activity_order_success.*

class OrderSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_success)
        addressSuccess.text = "${DataServices.userInputAddress[0].useraddress1}, ${DataServices.userInputAddress[0].useraddress2}"
    }
}