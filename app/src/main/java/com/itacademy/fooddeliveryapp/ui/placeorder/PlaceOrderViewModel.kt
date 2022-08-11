package com.itacademy.fooddeliveryapp.ui.placeorder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.fooddeliveryapp.data.local.Resource
import com.itacademy.fooddeliveryapp.data.model.Response
import com.itacademy.fooddeliveryapp.data.retrofit.OrderRepository
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class PlaceOrderViewModel(private val orderRepository: OrderRepository) : ViewModel() {

    //place order
    private val placeOrder = MutableLiveData<Resource<Response<String>>>()
    val placeOrderStatus: LiveData<Resource<Response<String>>>
        get() = placeOrder

    fun placeOrder(orderId: String) {
        viewModelScope.launch {
            try {
                placeOrder.value = Resource.loading()
                val response = orderRepository.placeOrder(orderId)
                if(response.code==1){
                    placeOrder.value = Resource.success(response)
                }else{
                    placeOrder.value = Resource.error(null,response.message)
                }
            } catch (e: Exception) {
                println("place order failed ${e.message}")
                if (e is UnknownHostException) {
                    placeOrder.value = Resource.offlineError()
                } else {
                    placeOrder.value = Resource.error(e)
                }
            }
        }
    }

}