package com.itacademy.fooddeliveryapp.ui.contributors

import androidx.lifecycle.ViewModel
import com.itacademy.fooddeliveryapp.data.model.ContributorModel

class ContributorViewModel : ViewModel() {

    private var contributorList: ArrayList<ContributorModel> = arrayListOf()
    init {
        contributorList.clear()
        contributorList.add(
            ContributorModel(
                "Erkinov Shaamyrza",
                "Android Developer",
                "https://github.com/shrikanth7698",
                "shama.erkinov@gmail.com",
                "",
                "",
            )
        )
        contributorList.add(
            ContributorModel(
                "Timur Moldokanov",
                "Backend developer",
                "",
                "",
                "",
                "",
            )
        )
    }

    fun getContributor(contributorId: Int):ContributorModel {
        return contributorList[contributorId]
    }

}