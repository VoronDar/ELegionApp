package com.astery.elegionapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.astery.elegionapp.listeners.JobListener
import com.astery.elegionapp.repository.Repository

class LoginViewModel: ViewModel() {

    internal var repository:Repository? = null


    fun login(listener: JobListener, login: String, password:String){
        repository!!.login(login, password, listener)
    }
}