package com.itacademy.fooddeliveryapp

import android.app.Application
import com.itacademy.fooddeliveryapp.di.appModule
import com.itacademy.fooddeliveryapp.di.networkModule
import com.itacademy.fooddeliveryapp.di.viewModelModule
import com.itacademy.fooddeliveryapp.utils.PicassoImageLoadingService
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ss.com.bannerslider.Slider

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, networkModule, viewModelModule))
        }
        Slider.init(PicassoImageLoadingService(this))
    }

}