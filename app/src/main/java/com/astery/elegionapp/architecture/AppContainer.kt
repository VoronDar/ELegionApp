package com.astery.elegionapp.architecture

import android.content.Context
import com.astery.elegionapp.data_source.controller.DataController
import com.astery.elegionapp.data_source.local.database.AppDatabase
import com.astery.elegionapp.data_source.local.database.LocalDataSource
import com.astery.elegionapp.data_source.remote.ActualRemoteDataSource
import com.astery.elegionapp.data_source.remote.FakeRemoteDataSource
import com.astery.elegionapp.data_source.remote.RemoteDataSource
import com.astery.elegionapp.repository.Repository

class AppContainer(context: Context) {

    private val lDatabase:AppDatabase = AppDatabase.getDatabase(context);

    private val remoteDataSource:RemoteDataSource = FakeRemoteDataSource(context)
    private val localDataSource = LocalDataSource(lDatabase)

    private val dataController = DataController(localDataSource, remoteDataSource, context)

    val repository = Repository(dataController)

}