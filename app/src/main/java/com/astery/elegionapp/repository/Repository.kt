package com.astery.elegionapp.repository

import com.astery.elegionapp.data_source.controller.DataController
import com.astery.elegionapp.listeners.JobListener

class Repository(val dataController: DataController) {
    fun login(login: String, password: String, listener: JobListener) {
        // TODO
        listener.done(true)
    }

}