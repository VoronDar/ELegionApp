package com.astery.elegionapp.listeners

abstract class ResultListener : JobListener {
    private var listener: JobListener? = null
    abstract fun success()
    override fun done(success: Boolean) {
        if (listener != null) listener?.done(success)
        if (success) success()
    }

    fun setListener(listener: JobListener?): ResultListener {
        this.listener = listener
        return this
    }
}
