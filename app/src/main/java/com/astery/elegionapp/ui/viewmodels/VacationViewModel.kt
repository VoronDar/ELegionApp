package com.astery.elegionapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.astery.elegionapp.data_source.local.database.db_utils.LocalLoadable
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

    fun getVacationOptions(): List<String> {
        val list: ArrayList<String> = ArrayList()
        list.add("Оплачиваемый отпуск")
        list.add("Неоплачиваемый отпуск")
        list.add("Больничный до 3 дней")
        list.add("Больничный больше 3 дней")
        return list
    }

    fun sendVocation(result: Map<String, String?>, listener:LocalLoadable) {
        repository?.putValues(result, "vocation_request", listener)
    }

    fun getRequestOptions(): List<String> {
        val list: ArrayList<String> = ArrayList()
        list.add("Кафетерий")
        list.add("Командировочные")
        list.add("Оборудование")
        return list

    }
}