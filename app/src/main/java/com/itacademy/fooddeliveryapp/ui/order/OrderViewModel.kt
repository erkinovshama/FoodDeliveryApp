package com.itacademy.fooddeliveryapp.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.fooddeliveryapp.data.local.Resource
import com.itacademy.fooddeliveryapp.data.model.OrderItemListModel
import com.itacademy.fooddeliveryapp.data.model.OrderStatusRequest
import com.itacademy.fooddeliveryapp.data.model.RatingRequest
import com.itacademy.fooddeliveryapp.data.retrofit.OrderRepository
import java.net.UnknownHostException
import com.itacademy.fooddeliveryapp.data.model.Response
import kotlinx.coroutines.launch


class OrderViewModel(private val orderRepository: OrderRepository) : ViewModel() {

    //get order detail by order id
    private val orderByIdRequest = MutableLiveData<Resource<Response<OrderItemListModel>>>()
    val orderByIdResponse: LiveData<Resource<Response<OrderItemListModel>>>
        get() = orderByIdRequest

    fun getOrderById(orderId: Int, isSilent: Boolean = false) {
        viewModelScope.launch {
            try {
                if(!isSilent) {
                    orderByIdRequest.value = Resource.loading()
                }
                val response = orderRepository.getOrderById(orderId)
                if (response.code == 1)
                    orderByIdRequest.value = Resource.success(response)
                else {
                    orderByIdRequest.value = Resource.error(message = response.message)
                }

            } catch (e: Exception) {
                if (e is UnknownHostException) {
                    orderByIdRequest.value = Resource.offlineError()
                } else {
                    orderByIdRequest.value = Resource.error(e)
                }
            }
        }
    }

    //fetch orders
    private val performFetchOrders = MutableLiveData<Resource<List<OrderItemListModel>>>()
    val performFetchOrdersStatus: LiveData<Resource<List<OrderItemListModel>>>
        get() = performFetchOrders

    fun getOrders(mobile: String, pageNum: Int, pageCount: Int) {
        viewModelScope.launch {
            try {
                performFetchOrders.value = Resource.loading()
                val response = orderRepository.getOrders(mobile, pageNum, pageCount)
                if (!response.data.isNullOrEmpty()) {
                    performFetchOrders.value = Resource.success(response.data)
                } else {
                    performFetchOrders.value = Resource.empty()
                }
            } catch (e: Exception) {
                println("fetch orders failed ${e.message}")
                if (e is UnknownHostException) {
                    performFetchOrders.value = Resource.offlineError()
                } else {
                    performFetchOrders.value = Resource.error(e)
                }
            }
        }
    }

    //rate order
    private val rateOrder = MutableLiveData<Resource<Response<String>>>()
    val rateOrderStatus: LiveData<Resource<Response<String>>>
        get() = rateOrder

    fun rateOrder(ratingRequest: RatingRequest) {
        viewModelScope.launch {
            try {
                rateOrder.value = Resource.loading()
                val response = orderRepository.rateOrder(ratingRequest)
                if (response.data != null) {
                    rateOrder.value = Resource.success(response)
                } else {
                    rateOrder.value = Resource.error(null, response.message)
                }
            } catch (e: Exception) {
                println("rate order failed ${e.message}")
                if (e is UnknownHostException) {
                    rateOrder.value = Resource.offlineError()
                } else {
                    rateOrder.value = Resource.error(e)
                }
            }
        }
    }

    //cancel order
    private val cancelOrder = MutableLiveData<Resource<Response<String>>>()
    val cancelOrderStatus: LiveData<Resource<Response<String>>>
        get() = cancelOrder

    fun cancelOrder(orderStatusRequest: OrderStatusRequest) {
        viewModelScope.launch {
            try {
                cancelOrder.value = Resource.loading()
                val response = orderRepository.cancelOrder(orderStatusRequest)
                if (response.data != null) {
                    cancelOrder.value = Resource.success(response)
                } else {
                    cancelOrder.value = Resource.error(null, response.message)
                }
            } catch (e: Exception) {
                println("cancel order failed ${e.message}")
                if (e is UnknownHostException) {
                    cancelOrder.value = Resource.offlineError()
                } else {
                    cancelOrder.value = Resource.error(e)
                }
            }
        }
    }

}