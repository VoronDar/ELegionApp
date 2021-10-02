package com.astery.elegionapp.ui.adapters.units

class Request(val state: RequestState,val title: String)

enum class RequestState{
    accepted,
    inProgress,
    wait
}