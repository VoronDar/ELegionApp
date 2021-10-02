package com.astery.elegionapp.data_source.remote

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.astery.elegionapp.data_source.local.database.db_utils.LocalLoadable
import com.astery.elegionapp.data_source.rx_utils.RxExecutable
import com.astery.elegionapp.data_source.rx_utils.RxLoadable
import com.astery.elegionapp.data_source.rx_utils.RxTaskManager
import com.astery.elegionapp.repository.listeners.GetItemListener
import com.google.gson.Gson
import io.reactivex.ObservableEmitter
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.lang.RuntimeException

class ActualRemoteDataSource(private val context: Context) :RemoteDataSource {
    private val domen = "http://192.168.43.109:8000/api"


    override fun <T> getValuesWithParent(
        parentId: String?,
        listener: GetItemListener<List<T>?>?,
        className: Class<T>
    ) {
        TODO()
    }

    override fun <T> getValues(listener: GetItemListener<List<T>?>?, className: Class<T>) {
        TODO()
    }

    override fun <T> getAllData(
        loadable: GetItemListener<List<T>?>?,
        collectionName: String?,
        className: Class<T>?
    ) {
        TODO()
    }

    override fun <T> getDataById(
        loadable: GetItemListener<T>?,
        collectionName: String?,
        id: String?,
        className: Class<T>?
    ) {
        TODO()
    }


    override fun <T> getData(listener: GetItemListener<List<T>>, className: Class<T>) {
        TODO("Not yet implemented")
    }

    private fun <T> getListOfData(
        listener: GetItemListener<List<T>>,
        className: Class<T>,
        token: String
    ) {
        RxTaskManager.loadValue(object : RxLoadable() {
            override fun loadSomething(em: ObservableEmitter<Any?>) {
                val queue: RequestQueue = Volley.newRequestQueue(context)
                val url: String = domen + "/" + getRoute(className)
                Log.i("main", url)
                val stringRequest = StringRequest(
                    Request.Method.GET, url,
                    { response ->
                        if (response.equals("f")) {
                            em.onError(getError())
                        } else {
                            val json: JSONObject
                            try {
                                json = JSONObject(response)
                                val list = ArrayList<T>()
                                // TODO ()
                                em.onNext(list)
                            } catch (e: JSONException) {
                                e.printStackTrace()
                                Log.i("error", e.message!!);
                                em.onError(getError())
                            }
                        }
                    }) {
                    Log.i("error", it.message!!);
                    em.onError(getError())
                }

                Log.i("main", stringRequest.headers.toString());
                //"access": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjMzMTYxODg4LCJqdGkiOiI4MTY1MzFiMjczNGQ0NjA3OTFhY2NhZTNiMjE4NjllZCIsInVzZXJfaWQiOjF9.cyYOtfFFHFuIBgokJsxpg31CZ44AHMSRbF2FWpyfIhg"
                val t =
                    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjMzMTY0MDcxLCJqdGkiOiI3YzA1NDg2OTAxOWY0ZWFlOWJmYmVlNmNkZTE4MmNkNyIsInVzZXJfaWQiOjN9.M4OE7f5MKUv5XNHFyWnR8NrwZ6_Oyh2mzHrUm2cGyJk"
                Log.i("main", stringRequest.headers.toString());
                Log.i("main", "3")
                queue.add(stringRequest)
            }

            override fun onErrorListener() {
                listener.error()
            }
        }, listener)
    }

    private fun <T> getSingleData(
        listener: GetItemListener<T>,
        className: Class<T>,
        token: String
    ) {
        RxTaskManager.loadValue(object : RxLoadable() {
            override fun loadSomething(em: ObservableEmitter<Any?>) {
                val queue: RequestQueue = Volley.newRequestQueue(context)
                val url: String = domen + "/" + getRoute(className)
                Log.i("main", url)
                val stringRequest = StringRequest(
                    Request.Method.GET, url,
                    { response ->
                        if (response.equals("f")) {
                            em.onError(getError())
                        } else {
                            val json: JSONObject
                            try {
                                json = JSONObject(response)
                                val t: T = Gson().fromJson(json.toString(), className);
                                em.onNext(t!!)
                            } catch (e: JSONException) {
                                e.printStackTrace()
                                Log.i("error", e.message!!);
                                em.onError(getError())
                            }
                        }
                    }) {
                    Log.i("error", it.message!!);
                    em.onError(getError())
                }

                Log.i("main", stringRequest.headers.toString());
                //"access": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjMzMTYxODg4LCJqdGkiOiI4MTY1MzFiMjczNGQ0NjA3OTFhY2NhZTNiMjE4NjllZCIsInVzZXJfaWQiOjF9.cyYOtfFFHFuIBgokJsxpg31CZ44AHMSRbF2FWpyfIhg"
                val t =
                    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjMzMTY0MDcxLCJqdGkiOiI3YzA1NDg2OTAxOWY0ZWFlOWJmYmVlNmNkZTE4MmNkNyIsInVzZXJfaWQiOjN9.M4OE7f5MKUv5XNHFyWnR8NrwZ6_Oyh2mzHrUm2cGyJk"
                Log.i("main", stringRequest.headers.toString());
                Log.i("main", "3")
                queue.add(stringRequest)
            }

            override fun onErrorListener() {
                listener.error()
            }
        }, listener)
    }


    /** push values  */
    override fun <T> pushValues(list: T, loadable: LocalLoadable?, className: String) {
        RxTaskManager.doTask(object : RxExecutable() {
            override fun doSomething() {
            }

            override fun onCompleteListener() {
                loadable?.onCompleteListener()
            }

            override fun onErrorListener() {
                loadable?.onErrorListener()
            }
        })
    }

    fun hasConnection(context: Context): Boolean {
        // TODO(change this one)
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        wifiInfo = cm.activeNetworkInfo
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        return false
    }

    private fun getError(): Exception {
        return RuntimeException()
    }

    private fun <T> getRoute(className: Class<T>): String {
        return when (className.simpleName) {
            "User" -> "user"
            else -> throw RuntimeException("getRoute unknown name " + className.simpleName)
        }
    }

    override fun <T> pushMap(map: Map<String, T>, route: String, loadable: LocalLoadable?) {
        TODO("Not yet implemented")
    }
}