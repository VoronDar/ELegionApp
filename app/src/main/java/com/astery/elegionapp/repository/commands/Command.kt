package com.astery.elegionapp.repository.commands

import com.astery.elegionapp.listeners.JobListener


/** items that allow to do job with data one after one and in the end - notify parent */
class Command(private val task: CommandTask) {
    var next: Command? = null
        private set
    private var listener: JobListener? = null
    private var parentListener: JobListener? = null
    fun work() {
        task.work(listener)
    }

    public fun setNext(next: Command) {
        if (this.next == null) {
            this.next = next
            this.next!!.setParentListener(parentListener)
        } else {
            this.next!!.setNext(next)
        }
    }

    fun setParentListener(parentListener: JobListener?) {
        this.parentListener = parentListener
        listener = object : JobListener {
            override fun done(success: Boolean) {
                if (success) {
                    if (next == null) {
                        parentListener?.done(true)
                    } else {
                        next!!.work()
                    }
                } else parentListener?.done(false)
            }
        }
        if (next != null) next!!.setParentListener(parentListener)
    }

    interface CommandTask {
        fun work(listener: JobListener?)
    }

    interface GetItemInCommand<T> {
        fun getItem(item: T)
    }
}