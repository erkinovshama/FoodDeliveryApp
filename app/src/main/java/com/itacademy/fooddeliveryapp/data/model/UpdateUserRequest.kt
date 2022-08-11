package com.itacademy.fooddeliveryapp.data.model

import com.google.gson.annotations.SerializedName

data class UpdateUserRequest(
    @SerializedName("placeModel")
    val placeModel: PlaceModel,
    @SerializedName("userModel")
    val userModel: UserModel
)