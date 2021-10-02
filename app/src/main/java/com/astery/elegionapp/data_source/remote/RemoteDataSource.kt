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

interface RemoteDataSource {
    fun <T> getValuesWithParent(
        parentId: String?,
        listener: GetItemListener<List<T>?>?,
        className: Class<T>
    );

    fun <T> getValues(listener: GetItemListener<List<T>?>?, className: Class<T>);

    fun <T> getAllData(
        loadable: GetItemListener<List<T>?>?,
        collectionName: String?,
        className: Class<T>?
    );

    fun <T> getDataById(
        loadable: GetItemListener<T>?,
        collectionName: String?,
        id: String?,
        className: Class<T>?
    );

    fun <T> getData(listener: GetItemListener<List<T>>, className: Class<T>)
    /** push values  */
    fun <T> pushValues(list: T, loadable: LocalLoadable?, className: String)
    fun <T> pushMap(map :Map<String, T>, route :String, loadable: LocalLoadable?)
}