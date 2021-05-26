package com.example.razorpaydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.razorpaydemo.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity(), PaymentResultListener {
    private lateinit var binding: ActivityMainBinding
    private var samount : String = "100"
    private var amount : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        amount = Math.round(samount.toFloat() * 100)
        binding.buttonPay.setOnClickListener {
            val checkout = Checkout()
            checkout.setKeyID("rzp_test_A22Wx1ZvMhFScd")
            checkout.setImage(R.drawable.rzp_logo)
            var jsonObject = JSONObject()
            try {
                jsonObject.put("name", "Android Coding")
                jsonObject.put("description", "Amount Payed")
                jsonObject.put("theme color", "#0093dd")
                jsonObject.put("currency", "INR")
                jsonObject.put("amount", amount)
                jsonObject.put("mobile number", "9001638396")
                checkout.open(this, jsonObject)
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

    override fun onPaymentSuccess(p0: String?) {
        val alertDialogBuilder = MaterialAlertDialogBuilder(this)
        alertDialogBuilder.setTitle("Payment ID")
        alertDialogBuilder.setMessage(p0)
        alertDialogBuilder.show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this,
        p1,
        Toast.LENGTH_LONG)
            .show()
    }
}