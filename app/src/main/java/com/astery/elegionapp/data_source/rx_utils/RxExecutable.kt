package com.astery.elegionapp.data_source.rx_utils

abstract class RxExecutable {
    abstract fun doSomething()
    abstract fun onCompleteListener()
    abstract fun onErrorListener()
}