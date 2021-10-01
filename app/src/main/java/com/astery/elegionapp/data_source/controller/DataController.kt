package com.astery.elegionapp.data_source.controller

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.astery.elegionapp.data_source.local.database.LocalDataSource
import com.astery.elegionapp.data_source.local.database.db_utils.LocalLoadable
import com.astery.elegionapp.data_source.remote.RemoteDataSource
import com.astery.elegionapp.repository.listeners.GetItemListener
import com.astery.elegionapp.repository.listeners.JobListener
import io.reactivex.observers.DisposableSingleObserver
import java.util.*

class DataController(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val context: Context
) {
    /** load from remote and push to local  */
    fun <T> loadValuesFromRemote(listener: JobListener, className: Class<T>) {
        if (connected()) {
            Log.i("main", "get from remote")
            remoteDataSource.getValues(object : GetItemListener<List<T>?> {
                override fun getItem(list: List<T>?) {
                    pushDataToLocal(list, listener, className)
                }

                override fun error() {
                    listener.done(false)
                }
            }, className)
        } else {
            listener.done(false)
        }
    }

    /** load from remote, push to local, if it is not in remote - get from local, than get to the client */
    fun <T> getValuesFromRemoteById(
        listener: GetItemListener<T>,
        id: String?,
        className: Class<T>
    ) {
        if (connected()) {
            remoteDataSource.getDataById(object : GetItemListener<T> {
                override fun getItem(item: T) {
                    if (item != null) {
                        localDataSource.loadValue(item, null, className.simpleName)
                        listener.getItem(item)
                    } else {
                        getValuesFromLocalById(listener, id, className)
                    }
                }

                override fun error() {
                    getValuesFromLocalById(listener, id, className)
                }
            }, className.simpleName, id, className)
        } else {
            listener.error()
            getValuesFromLocalById(listener, id, className)
        }
    }

    /** load from local, if it is not in local - get from remote, then push to local...  */
    fun <T> getValuesFromLocalById(listener: GetItemListener<T>, id: String?, className: Class<T>) {
        localDataSource.getValuesById(object : DisposableSingleObserver<T>() {
            override fun onSuccess(t: T) {
                listener.getItem(t)
            }

            override fun onError(e: Throwable) {
                if (connected()) getValuesFromRemoteById(
                    listener,
                    id,
                    className
                ) else listener.error()
            }
        }, id, className.simpleName)
    }

    /** load from local, if it is not in local - get from remote, then push to local...  */
    fun <M> getValuesFromLocalByParent(
        listener: GetItemListener<List<M>?>,
        parentId: String?,
        className: Class<M>
    ) {
        localDataSource.getValuesWithParent(
            parentId,
            object : DisposableSingleObserver<List<M>?>() {
                override fun onSuccess(t: List<M>) {
                    listener.getItem(t)
                }

                override fun onError(e: Throwable) {
                    if (connected()) getValuesFromRemoteByParent(
                        listener,
                        parentId,
                        className
                    ) else listener.error()
                }
            },
            className.simpleName
        )
    }

    /** load from remote, push to local, if it is not in remote - get from local, than get to the client */
    fun <M> getValuesFromRemoteByParent(
        listener: GetItemListener<List<M>?>,
        parentId: String?,
        className: Class<M>
    ) {
        if (connected()) {
            remoteDataSource.getValuesWithParent(parentId, object : GetItemListener<List<M>?> {
                override fun getItem(list: List<M>?) {
                    localDataSource.loadValues(list, null, className.simpleName)
                    listener.getItem(list)
                }

                override fun error() {
                    getValuesFromLocalByParent(listener, parentId, className)
                }
            }, className)
        }
    }

    /** load from local and get to the client  */
    fun <T> getValuesFromLocal(listener: GetItemListener<List<T>?>, className: Class<T>) {
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

    /** load from local and get to the client  */
    fun <T> getValuesFromLocalByDay(
        listener: GetItemListener<List<T>?>,
        date: Date?,
        className: Class<T>
    ) {
        Log.i("main", "get from local")
        localDataSource.getValuesByDate(object : DisposableSingleObserver<List<T>?>() {
            override fun onSuccess(things: List<T>) {
                listener.getItem(things)
            }

            override fun onError(e: Throwable) {
                Log.i("main", e.message!!)
                listener.error()
            }
        }, date, className.simpleName)
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

    /** return true if the device connected to the internet  */
    fun connected(): Boolean {
        TODO("find actual way to check connection")
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nInfo = cm.activeNetworkInfo
        return nInfo != null && nInfo.isAvailable && nInfo.isConnected
    }
}