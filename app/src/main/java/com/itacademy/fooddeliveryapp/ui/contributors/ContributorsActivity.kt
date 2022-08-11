package com.itacademy.fooddeliveryapp.ui.contributors

import android.animation.LayoutTransition
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.itacademy.fooddeliveryapp.R
import com.itacademy.fooddeliveryapp.data.local.PreferencesHelper
import com.itacademy.fooddeliveryapp.databinding.ActivityContributorsBinding
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ContributorsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContributorsBinding
    private val preferencesHelper: PreferencesHelper by inject()
    private val viewModel by viewModel<ContributorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setListener()
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contributors)
        binding.layoutContent.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        Picasso.get().load(viewModel.getContributor(0).image).into(binding.imageShama)
        Picasso.get().load(viewModel.getContributor(0).image).into(binding.imageTimur)
    }

    private fun setListener(){
        binding.imageClose.setOnClickListener {
            onBackPressed()
        }
        binding.layoutShama.setOnClickListener {
            val intent = Intent(applicationContext,ContributorDetailActivity::class.java)
            intent.putExtra("contributor_id",0)
            startActivity(intent)
        }
        binding.layoutTimur.setOnClickListener {
            val intent = Intent(applicationContext,ContributorDetailActivity::class.java)
            intent.putExtra("contributor_id",0)
            startActivity(intent)
        }
    }
}