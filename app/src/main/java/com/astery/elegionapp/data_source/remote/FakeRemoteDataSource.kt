package com.astery.elegionapp.data_source.remote

import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import com.astery.elegionapp.data_source.local.database.db_utils.LocalLoadable
import com.astery.elegionapp.data_source.rx_utils.RxExecutable
import com.astery.elegionapp.data_source.rx_utils.RxTaskManager
import com.astery.elegionapp.repository.listeners.GetItemListener
import com.astery.elegionapp.ui.adapters.units.*
import java.util.*
import kotlin.collections.ArrayList

class FakeRemoteDataSource(private val context: Context) : RemoteDataSource {
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
        when (className.simpleName) {
            "Request" -> {
                val list = ArrayList<Request>()
                list.add(Request(RequestState.wait, "Покупка ноутбука"))
                list.add(Request(RequestState.inProgress, "Оплата курсов по Kotlin"))
                list.add(Request(RequestState.accepted, "Покупка клавиатуры"))
                listener.getItem(list as List<T>)
            }
            "Vacation" -> {
                var cal = Calendar.getInstance()
                val list = ArrayList<Vacation>()
                cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1)
                list.add(Vacation("Иван Лисицин", cal, "asdasd", null, isActive = true))
                cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 5)
                list.add(Vacation("Иван Лисицин", cal, "234", null, isActive = true))
                cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1)
                list.add(Vacation("Иван Лисицин", cal, "3243", null, isActive = true))
                cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1)
                list.add(Vacation("Иван Лисицин", cal, "2222", null, isActive = true))
                listener.getItem(list as List<T>)
            }
            "VacationPlan" -> {
                var cal = Calendar.getInstance()
                val list = ArrayList<Vacation>()
                cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1)
                list.add(Vacation("Иван Лисицин", cal, "asdasd", null, isActive = true))
                cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 5)
                list.add(Vacation("Иван Лисицин", cal, "234", null, isActive = true))
                cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1)
                list.add(Vacation("Иван Лисицин", cal, "3243", null, isActive = true))
                cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1)
                list.add(Vacation("Иван Лисицин", cal, "2222", null, isActive = true))
                listener.getItem(list as List<T>)
            }
            "RoadMap" -> {
                val list = ArrayList<RoadMap>()
                val cal = Calendar.getInstance()
                list.add(RoadMap("JR Android developer", -1, null, 600))
                list.add(RoadMap("JR+ Android developer", -1, null, 650))
                list.add(RoadMap("Middle Android developer", 0, cal, 800))
                list.add(RoadMap("Senior Android developer", 1, null, 1000))
                listener.getItem(list as List<T>)
            }
            "Task" -> {
                val list = ArrayList<Task>()
                list.add(Task("Изучить android studio", 1))
                list.add(Task("Изучить RxJava", 0))
                list.add(Task("Изучить Retrofit", 1))
                listener.getItem(list as List<T>)
            }
            "TaskTask" -> {
                val list = ArrayList<Task>()
                list.add(Task("40 комиттов на github", 1))
                list.add(Task("1000 строчек кода", 0))
                list.add(Task("15 страниц с получением данных", 1))
                listener.getItem(list as List<T>)
            }
            "Skill" -> {
                val list = ArrayList<Skill>()
                list.add(Skill("Android studio"))
                list.add(Skill("Kotlin"))
                list.add(Skill("Java"))
                listener.getItem(list as List<T>)
            }
        }
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

    private fun getError(): Exception{
        return RuntimeException()
    }

    private fun <T> getRoute(className: Class<T>):String{
        return when (className.simpleName){
            "User" -> "user"
            else -> throw RuntimeException("getRoute unknown name " + className.simpleName)
        }
    }

    override fun <T> pushMap(map: Map<String, T>, route:String, loadable: LocalLoadable?) {
        loadable?.onCompleteListener()
    }
}