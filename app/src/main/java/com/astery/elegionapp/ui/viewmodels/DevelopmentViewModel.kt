package com.astery.elegionapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.astery.elegionapp.data_source.local.database.db_utils.LocalLoadable
import com.astery.elegionapp.repository.Repository
import com.astery.elegionapp.ui.adapters.units.*

class DevelopmentViewModel: ViewModel() {

    val roadMap: LiveData<List<RoadMap>> = MutableLiveData()
    val goals: LiveData<List<Task>> = MutableLiveData()
    val tasks: LiveData<List<Task>> = MutableLiveData()
    val skills: LiveData<List<Skill>> = MutableLiveData()

    internal var repository:Repository? = null


    fun getData(){
        repository?.getValues(roadMap, RoadMap::class.java)
        repository?.getValues(goals, Task::class.java)
        repository?.getValues(tasks, TaskTask::class.java as Class<Task>)
        repository?.getValues(skills, Skill::class.java)
    }

    fun sendGoal(result: Map<String, String?>, listener:LocalLoadable) {
        repository?.putValues(result, "push_goal", listener)
    }
    fun sendTask(result: Map<String, String?>, listener:LocalLoadable) {
        repository?.putValues(result, "push_task", listener)
    }
}