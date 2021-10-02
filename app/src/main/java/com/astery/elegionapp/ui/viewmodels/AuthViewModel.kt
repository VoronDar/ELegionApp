package com.astery.elegionapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.astery.elegionapp.listeners.JobListener
import com.astery.elegionapp.repository.Repository

class AuthViewModel: ViewModel() {

    internal var repository:Repository? = null


    fun auth(listener: JobListener, name:String, surname:String, phone:String, password:String, submit:String){
        if (password == submit)
            repository!!.auth(name, surname, phone, password, listener)
        else
            listener.done(false)
    }
}