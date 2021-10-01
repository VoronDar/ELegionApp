package com.astery.elegionapp.architecture

import android.app.Application
import com.astery.elegionapp.data_source.local.database.AppDatabase

class App: Application() {

    val container:AppContainer by lazy {
        AppContainer(this)
    };




}