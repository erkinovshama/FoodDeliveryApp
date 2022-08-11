package com.itacademy.fooddeliveryapp.di
import com.itacademy.fooddeliveryapp.ui.cart.CartViewModel
import com.itacademy.fooddeliveryapp.ui.contributors.ContributorViewModel
import com.itacademy.fooddeliveryapp.ui.home.HomeViewModel
import com.itacademy.fooddeliveryapp.ui.login.LoginViewModel
import com.itacademy.fooddeliveryapp.ui.order.OrderViewModel
import com.itacademy.fooddeliveryapp.ui.otp.OtpViewModel
import com.itacademy.fooddeliveryapp.ui.payment.PaymentViewModel
import com.itacademy.fooddeliveryapp.ui.placeorder.PlaceOrderViewModel
import com.itacademy.fooddeliveryapp.ui.profile.ProfileViewModel
import com.itacademy.fooddeliveryapp.ui.restaurant.RestaurantViewModel
import com.itacademy.fooddeliveryapp.ui.search.SearchViewModel
import com.itacademy.fooddeliveryapp.ui.signup.SignUpViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { OtpViewModel(get()) }
    viewModel { RestaurantViewModel(get()) }
    viewModel { SignUpViewModel(get(), get()) }
    viewModel { ProfileViewModel(get(), get(),get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { OrderViewModel(get()) }
    viewModel { CartViewModel(get()) }
    viewModel { PlaceOrderViewModel(get()) }
    viewModel { PaymentViewModel(get()) }
    viewModel { ContributorViewModel() }
}