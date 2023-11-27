package com.example.kotlin.robertoruizapp.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.Document
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.MyCourseObject
import com.example.kotlin.robertoruizapp.domain.MyCoursesListRequirement
import com.example.kotlin.robertoruizapp.framework.adapters.viewholder.MyCoursesViewHolder
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel class for handling data and business logic related to COVID-19 cases by country
 */
class MyCoursesViewModel : ViewModel() {
    // LiveData to observe changes in the list of countries
    val LiveData = MutableLiveData<MyCourseObject>()

    // Instance of the ListRequirement class for data retrieval
    private val listRequirement = MyCoursesListRequirement()

    /**
     * Function to fetch the list of countries for a given date
     *
     * @param date String representing the date for which the COVID-19 cases are requested
     */
    fun getList() {
        // Launch a coroutine in the IO dispatcher to perform the data retrieval
        viewModelScope.launch(Dispatchers.IO) {
            // Call the ListRequirement function to fetch the list
            val result: MyCourseObject? = listRequirement("Bearer ${LoginActivity.token}")

            // Use the Main dispatcher to update the LiveData with the fetched result
            CoroutineScope(Dispatchers.Main).launch {
                LiveData.postValue(result!!)
            }
        }
    }
}