package com.astery.elegionapp.repository.listeners

interface GetItemListener<T> {
    fun getItem(item: T)
    fun error()
}