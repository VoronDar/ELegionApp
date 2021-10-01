package com.astery.elegionapp.repository.commands

import com.astery.elegionapp.listeners.JobListener
import com.astery.elegionapp.repository.commands.Command.CommandTask
import java.util.*

/** manager for commands those can get objects (created for do several task together) */
class TogetherCommandManager {
    private val commands: MutableList<Command>
    private val done: MutableList<Boolean>
    private var unionListener: JobListener? = null
    val command: Command
        get() = Command(object : CommandTask {
            override fun work(listener: JobListener?) {
                unionListener = listener
                for (command in commands) {
                    command.work()
                }
                if (commands.size == 0) listener!!.done(true)
            }
        })

    fun setCommand(task: CommandTask?) {
        setCommand(Command(task!!))
    }

    public fun setCommand(command: Command) {
        commands.add(command)
        done.add(false)
        commands[commands.size - 1].setParentListener(object : JobListener {
            override fun done(success: Boolean) {
                if (success) {
                    done[commands.indexOf(command)] = true
                    if (!done.contains(java.lang.Boolean.FALSE)) unionListener!!.done(true)
                } else unionListener!!.done(false)
            }
        })
    }

    init {
        commands = ArrayList()
        done = ArrayList()
    }
}