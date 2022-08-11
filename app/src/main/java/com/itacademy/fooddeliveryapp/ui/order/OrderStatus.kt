package com.itacademy.fooddeliveryapp.ui.order

import com.itacademy.fooddeliveryapp.data.model.OrderStatusModel

data class OrderStatus(
    var isDone: Boolean = false,
    var isCurrent: Boolean = false,
    var name: String,
    var orderStatusList: List<OrderStatusModel> = listOf()
)
