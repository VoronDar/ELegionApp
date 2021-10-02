package com.astery.elegionapp.data_source.controller

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.astery.elegionapp.data_source.local.database.LocalDataSource
import com.astery.elegionapp.data_source.local.database.db_utils.LocalLoadable
import com.astery.elegionapp.data_source.remote.ActualRemoteDataSource
import com.astery.elegionapp.data_source.remote.RemoteDataSource
import com.astery.elegionapp.listeners.JobListener
import com.astery.elegionapp.repository.listeners.GetItemListener
import io.reactivex.observers.DisposableSingleObserver
import java.util.*

class DataController(
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource,
    private val context: Context
) {
    /** load from local and get to the client  */
    fun <T> getValuesFromLocal(listener: GetItemListener<List<T>>, className: Class<T>) {
        Log.i("main", "get from local")
        localDataSource.getValues(object : DisposableSingleObserver<List<T>?>() {
            override fun onSuccess(things: List<T>) {
                listener.getItem(things)
            }

            override fun onError(e: Throwable) {
                Log.i("main", e.message!!)
                listener.error()
            }
        }, className.simpleName)
    }

    /** put data in local  */
    fun <T, M> pushDataToLocal(list: T, listener: JobListener?, className: Class<M>) {
        localDataSource.loadValues(list, object : LocalLoadable {
            override fun onCompleteListener() {
                listener?.done(true)
            }

            override fun onErrorListener() {
                listener?.done(false)
            }
        }, className.simpleName)
    }


    /** load from remote, push to local, if it is not in remote - get from local, than get to the client */
    fun <T> getValuesFromRemote(
        listener: GetItemListener<List<T>>,
        className: Class<T>
    ) {
        if (connected()) {
            remoteDataSource.getData(object : GetItemListener<List<T>> {
                override fun getItem(item: List<T>) {
                    if (item != null) {
                        localDataSource.loadValue(item, null, className.simpleName)
                        listener.getItem(item)
                    } else {
                        getValuesFromLocal(listener, className)
                    }
                }

                override fun error() {
                    getValuesFromLocal(listener, className)
                }
            }, className)
        } else {
            listener.error()
            getValuesFromLocal(listener, className)
        }
    }



    /** return true if the device connected to the internet  */
    fun connected(): Boolean {
        //TODO("find actual way to check connection")
        if (true) return true
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nInfo = cm.activeNetworkInfo
        return nInfo != null && nInfo.isAvailable && nInfo.isConnected
    }
}