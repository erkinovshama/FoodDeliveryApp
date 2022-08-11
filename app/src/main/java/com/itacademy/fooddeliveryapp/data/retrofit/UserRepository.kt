package com.itacademy.fooddeliveryapp.data.retrofit

import com.itacademy.fooddeliveryapp.data.model.LoginRequest
import com.itacademy.fooddeliveryapp.data.model.NotificationTokenUpdate
import com.itacademy.fooddeliveryapp.data.model.UpdateUserRequest
import retrofit2.Retrofit

class UserRepository(retrofit: Retrofit) {
    private val services = retrofit.create(CustomApi::class.java)
    suspend fun login(loginRequest: LoginRequest) = services.login(loginRequest)
    suspend fun updateUser(updateUserRequest: UpdateUserRequest) = services.updateUser(updateUserRequest)
    suspend fun updateFcmToken(notificationTokenUpdate: NotificationTokenUpdate) = services.updateFcmToken(notificationTokenUpdate)
}