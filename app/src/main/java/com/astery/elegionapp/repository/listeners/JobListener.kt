package com.astery.elegionapp.repository.listeners

interface JobListener {
    fun done(success: Boolean)
}