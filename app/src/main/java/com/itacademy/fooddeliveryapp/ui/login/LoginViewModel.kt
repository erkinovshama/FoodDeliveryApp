package com.itacademy.fooddeliveryapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.fooddeliveryapp.data.local.Resource
import com.itacademy.fooddeliveryapp.data.model.LoginRequest
import com.itacademy.fooddeliveryapp.data.model.Response
import com.itacademy.fooddeliveryapp.data.model.UserPlaceModel
import com.itacademy.fooddeliveryapp.data.retrofit.UserRepository
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    //LOGIN
    private val performLogin = MutableLiveData<Resource<Response<UserPlaceModel>>>()
    val performLoginStatus: LiveData<Resource<Response<UserPlaceModel>>>
        get() = performLogin

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {
                performLogin.value = Resource.loading()
                val response = userRepository.login(loginRequest)
                performLogin.value = Resource.success(response)
            } catch (e: Exception) {
                println("login failed ${e.message}")
                if (e is UnknownHostException) {
                    performLogin.value = Resource.offlineError()
                } else {
                    performLogin.value = Resource.error(e)
                }
            }
        }
    }

}