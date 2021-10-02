package com.astery.elegionapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.astery.elegionapp.data_source.controller.DataController
import com.astery.elegionapp.data_source.local.database.db_utils.LocalLoadable
import com.astery.elegionapp.listeners.JobListener
import com.astery.elegionapp.pojo.User
import com.astery.elegionapp.repository.listeners.GetItemListener

class Repository(private val dataController: DataController) {
    fun login(login: String, password: String, listener: JobListener) {
        // TODO
        listener.done(true)
    }
    fun auth(name: String, surname:String, password: String, phone:String, listener: JobListener) {
        // TODO
        listener.done(true)
    }

    fun getUser(listener:GetItemListener<List<User>>){
        dataController.remoteDataSource.getData(listener, User::class.java)
        // TODO (add method in data controller)
    }

    fun <T> getValues(values :LiveData<List<T>>, className :Class<T>){
        dataController.getValuesFromRemote(object:GetItemListener<List<T>>{
            override fun getItem(item: List<T>) {
                (values as MutableLiveData).value = item
            }

            override fun error() {
                TODO("Not yet implemented")
            }
        }, className)
    }

    fun <T> putValues(map: Map<String, T>, route:String, listener: LocalLoadable) {
        dataController.remoteDataSource.pushMap(map, route, listener)

    }

}