package com.itacademy.fooddeliveryapp.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.fooddeliveryapp.data.local.Resource
import com.itacademy.fooddeliveryapp.data.model.PlaceModel
import com.itacademy.fooddeliveryapp.data.model.UpdateUserRequest
import com.itacademy.fooddeliveryapp.data.retrofit.PlaceRepository
import com.itacademy.fooddeliveryapp.data.retrofit.UserRepository
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import com.itacademy.fooddeliveryapp.data.model.Response
import java.util.*
import kotlin.collections.ArrayList

class SignUpViewModel(private val userRepository: UserRepository, private val placeRepository: PlaceRepository) : ViewModel() {

    //Fetch places list
    private val performFetchPlacesList = MutableLiveData<Resource<Response<List<PlaceModel>>>>()
    val performFetchPlacesStatus: LiveData<Resource<Response<List<PlaceModel>>>>
        get() = performFetchPlacesList

    private var placesList: ArrayList<PlaceModel> = ArrayList()
    fun getPlaces() {
        viewModelScope.launch {
            try {
                performFetchPlacesList.value = Resource.loading()
                val response = placeRepository.getPlaces()
                if (response.code == 1) {
                    if (!response.data.isNullOrEmpty()) {
                        placesList.clear()
                        placesList.addAll(response.data)
                        performFetchPlacesList.value = Resource.success(response)
                    } else {
                        if (response.data != null) {
                            if (response.data.isEmpty()) {
                                performFetchPlacesList.value = Resource.empty()
                            }
                        } else {
                            performFetchPlacesList.value = Resource.error(null, message = "Something went wrong!")
                        }
                    }
                } else {
                    performFetchPlacesList.value = Resource.error(null, message = response.message)
                }
            } catch (e: Exception) {
                println("fetch places list failed ${e.message}")
                if (e is UnknownHostException) {
                    performFetchPlacesList.value = Resource.offlineError()
                } else {
                    performFetchPlacesList.value = Resource.error(e)
                }
            }
        }
    }

    fun searchPlace(query: String?) {
        if(!query.isNullOrEmpty()) {
            val queryPlaceList = placesList.filter {
                it.name.lowercase(Locale.getDefault()).contains(
                    query.lowercase(Locale.getDefault())
                )
            }
            performFetchPlacesList.value = Resource.success(Response(1, queryPlaceList, ""))
        }else{
            performFetchPlacesList.value = Resource.success(Response(1, placesList, ""))
        }
    }

    private val performSignUp = MutableLiveData<Resource<Response<String>>>()
    val performSignUpStatus: LiveData<Resource<Response<String>>>
        get() = performSignUp

    fun signUp(updateUserRequest: UpdateUserRequest) {
        viewModelScope.launch {
            try {
                performSignUp.value = Resource.loading()
                val response = userRepository.updateUser(updateUserRequest)
                if (response.code == 1) {
                    if (response.data != null) {
                        performSignUp.value = Resource.success(response)
                    } else {
                        performSignUp.value = Resource.error(null, message = "Something went wrong")
                    }
                } else {
                    performSignUp.value = Resource.error(null, message = response.message)
                }
            } catch (e: Exception) {
                println("Sign Up failed ${e.message}")
                if (e is UnknownHostException) {
                    performSignUp.value = Resource.offlineError()
                } else {
                    performSignUp.value = Resource.error(e)
                }
            }
        }
    }

}