package com.astery.elegionapp.data_source.rx_utils

import io.reactivex.ObservableEmitter

abstract class RxLoadable{
    abstract fun loadSomething(e: ObservableEmitter<Any?>)
    abstract fun onErrorListener()
}