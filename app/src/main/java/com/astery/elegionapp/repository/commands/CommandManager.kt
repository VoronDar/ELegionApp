package com.astery.elegionapp.repository.commands

import android.util.Log
import com.astery.elegionapp.listeners.JobListener
import com.astery.elegionapp.repository.commands.Command.CommandTask
import com.astery.elegionapp.repository.commands.Command.GetItemInCommand
import com.astery.elegionapp.repository.listeners.GetItemListener

/** manager for commands those can get objects */
open class CommandManager<T>(clientListener: GetItemInCommand<T>) {
    private val innerListener: GetItemListener<T>
    protected var commandListener: JobListener? = null
    protected var afterTask: CommandTask? = null
    fun getCommand(task: CommandTask): Command {
        return Command(object : CommandTask {
            override fun work(listener: JobListener?) {
                commandListener = listener
                task.work(commandListener)
            }
        })
    }

    fun setNextCommands(afterTask: CommandTask?) {
        this.afterTask = afterTask
    }

    init {
        innerListener = object : GetItemListener<T> {
            override fun getItem(item: T) {
                clientListener.getItem(item)
                if (afterTask != null) afterTask!!.work(null)
                commandListener!!.done(true)
            }

            override fun error() {
                Log.i("load", "error")
                commandListener!!.done(false)
            }
        }
    }
}