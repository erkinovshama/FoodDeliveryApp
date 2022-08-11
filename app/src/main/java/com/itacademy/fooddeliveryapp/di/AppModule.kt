package com.itacademy.fooddeliveryapp.di

import com.google.gson.Gson
import com.itacademy.fooddeliveryapp.data.local.PreferencesHelper
import org.koin.dsl.module

val appModule = module {

    single {
        Gson()
    }

    single {
        PreferencesHelper(get())
    }

}