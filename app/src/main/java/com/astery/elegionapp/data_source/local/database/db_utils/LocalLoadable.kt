package com.astery.elegionapp.data_source.local.database.db_utils

interface LocalLoadable {
    fun onCompleteListener()
    fun onErrorListener()
}