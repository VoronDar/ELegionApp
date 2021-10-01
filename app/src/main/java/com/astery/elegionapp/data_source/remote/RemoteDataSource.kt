package com.astery.elegionapp.data_source.remote

import com.astery.elegionapp.repository.listeners.GetItemListener

class RemoteDataSource() {


    fun <T> getValuesWithParent(
        parentId: String?,
        listener: GetItemListener<List<T>?>?,
        className: Class<T>
    ) {
        TODO()
    }

    fun <T> getValues(listener: GetItemListener<List<T>?>?, className: Class<T>) {
        TODO()
    }

    fun <T> getAllData(
        loadable: GetItemListener<List<T>?>?,
        collectionName: String?,
        className: Class<T>?
    ) {
        TODO()
    }

    fun <T> getDataById(
        loadable: GetItemListener<T>?,
        collectionName: String?,
        id: String?,
        className: Class<T>?
    ) {
        TODO()
    }
}