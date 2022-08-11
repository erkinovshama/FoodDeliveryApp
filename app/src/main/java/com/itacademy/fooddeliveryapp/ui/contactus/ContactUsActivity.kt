package com.itacademy.fooddeliveryapp.ui.contactus

import android.animation.LayoutTransition
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.itacademy.fooddeliveryapp.R
import com.itacademy.fooddeliveryapp.data.local.PreferencesHelper
import com.itacademy.fooddeliveryapp.databinding.ActivityContactUsBinding
import org.koin.android.ext.android.inject

class ContactUsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactUsBinding
    private val preferencesHelper: PreferencesHelper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setListener()
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_us)
        binding.layoutContent.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
    }

    private fun setListener(){
        binding.imageClose.setOnClickListener {
            onBackPressed()
        }
    }

}