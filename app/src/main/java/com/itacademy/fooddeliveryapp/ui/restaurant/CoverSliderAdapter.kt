package com.itacademy.fooddeliveryapp.ui.restaurant

import com.itacademy.fooddeliveryapp.R
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder

class CoverSliderAdapter(var coverUrls: List<String>) : SliderAdapter() {
    override fun getItemCount(): Int {
        return coverUrls.size
    }
    override fun onBindImageSlide(position: Int, viewHolder: ImageSlideViewHolder) {
        viewHolder.bindImageSlide(coverUrls[position], R.drawable.shop_placeholder, R.drawable.shop_placeholder)
    }
}