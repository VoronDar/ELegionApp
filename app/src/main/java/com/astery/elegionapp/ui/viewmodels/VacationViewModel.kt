package com.astery.elegionapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.astery.elegionapp.repository.Repository
import com.astery.elegionapp.ui.adapters.units.Request
import com.astery.elegionapp.ui.adapters.units.Vacation

class VacationViewModel: ViewModel() {

    val requests: LiveData<List<Request>> = MutableLiveData()
    val disappeared: LiveData<List<Vacation>> = MutableLiveData()
    val planned: LiveData<List<Vacation>> = MutableLiveData()

    internal var repository:Repository? = null


    fun getData(){
        repository?.getValues(requests, Request::class.java)
        repository?.getValues(disappeared, Vacation::class.java)
        repository?.getValues(planned, Vacation::class.java)
    }
}