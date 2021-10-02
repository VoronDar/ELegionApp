@file:Suppress("UNCHECKED_CAST")

package com.astery.elegionapp.data_source.rx_utils

import android.util.Log
import com.astery.elegionapp.repository.listeners.GetItemListener
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

object RxTaskManager {
    @JvmStatic
    fun doTask(executable: RxExecutable) {
        Observable.create { e: ObservableEmitter<Any?> ->
            val ex = Executors.newSingleThreadScheduledExecutor()
            val future: Future<Boolean?> = ex.schedule<Boolean?>(
                {
                    executable.doSomething()
                    e.onComplete()
                    null
                }, 0, TimeUnit.SECONDS
            )
            e.setCancellable { future.cancel(true) }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Any?> {
                override fun onComplete() {
                    executable.onCompleteListener()
                }

                override fun onSubscribe(d: Disposable) {}
                override fun onNext(o: Any) {}
                override fun onError(e: Throwable) {
                    executable.onErrorListener()
                }
            })
    }

    @JvmStatic
    fun <T> loadValue(executable: RxLoadable, listener:GetItemListener<T>) {
        Observable.create { e: ObservableEmitter<Any?> ->
            val ex = Executors.newSingleThreadScheduledExecutor()
            val future: Future<Boolean?> = ex.schedule<Boolean?>(
                {
                    executable.loadSomething(e)
                    null
                }, 0, TimeUnit.SECONDS
            )
            e.setCancellable { future.cancel(true) }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Any?> {
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(o: Any) {
                    listener.getItem(o as T)
                }
                override fun onError(e: Throwable) {
                    Log.i("main", e.stackTraceToString());
                    executable.onErrorListener()
                    listener.error()
                }
            })
    }
}