package com.egecius.gtdx.ui

import com.egecius.gtdx.datatypes.TodoTask
import com.egecius.gtdx.db.Db
import java.util.*

internal class ListActivityPresenterImpl(private val view: ListActivityView, val db: Db) : ListActivityPresenter {

    override fun onCreate() {
        observeTasksInCloudDatabase()
        view.showProgressBar()
    }

    private fun observeTasksInCloudDatabase() {
        db.allTasks.subscribe { map ->
            view.hideProgressBar()
            updateTasks(map) }
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
        view.onTasksUpdated(list)
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
