package com.astery.elegionapp.data_source.local.database

import com.astery.elegionapp.data_source.local.database.db_utils.LocalLoadable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.*

class LocalDataSource(private val database: AppDatabase) {
    fun <T> getValuesWithParent(
        parentId: String?,
        observer: DisposableSingleObserver<T>?,
        className: String?
    ) {
        TODO()
    }

    fun <T> getValuesByDate(
        observer: DisposableSingleObserver<T>?,
        date: Date?,
        className: String?
    ) {
        TODO()
    }

    fun <T> getValuesById(
        observer: DisposableSingleObserver<T>?,
        id: String?,
        className: String?
    ) {
        TODO()
    }

    fun <T> getValues(observer: DisposableSingleObserver<T>?, className: String?) {
        TODO()}
    fun <T> loadValue(item: T, loadable: LocalLoadable?, className: String?) {
        //TODO()
        loadable?.onCompleteListener()
        }

    /** load all values  */
    fun <T> loadValues(list: T, loadable: LocalLoadable?, className: String?) {
        TODO()}

    /** helping method that subscribes an observer on single  */
    private fun <T> subscribe(item: Single<T>, observer: DisposableSingleObserver<T>) {
        item
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}