package com.egecius.gtdx.ui

import com.egecius.gtdx.datatypes.TodoTask
import com.egecius.gtdx.db.DbImpl
import com.google.firebase.database.FirebaseDatabase
import java.util.*

internal class ListActivityPresenterImpl(private val listActivity: ListActivityView) : ListActivityPresenter {

    private val db = DbImpl(FirebaseDatabase.getInstance())

    override fun onCreate() {
        observeTasksInCloudDatabase()
    }

    private fun observeTasksInCloudDatabase() {
        db.allTasks.subscribe { map -> updateTasks(map) }
    }

    override fun onNewTaskAdded(taskTitle: String) {
        addTaskToDb(taskTitle)
    }

    private fun addTaskToDb(taskTitle: String) {
        val taskToAdd = TodoTask(taskTitle)
        db.addTask(taskToAdd)
    }

    private fun updateTasks(map: Map<String, Map<*, *>>) {
        val list = extractToTaskList(map)
        listActivity.onTasksUpdated(list)
    }

    private fun extractToTaskList(map: Map<String, Map<*, *>>): List<TodoTask> {

        val list = ArrayList<TodoTask>()

        for ((key, taskMap) in map) {
            val title = taskMap["title"] as String
            val todoTask = TodoTask(title)
            list.add(todoTask)
        }

        return list
    }

}
