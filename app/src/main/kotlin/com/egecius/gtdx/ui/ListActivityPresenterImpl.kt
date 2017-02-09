package com.egecius.gtdx.ui

import com.egecius.gtdx.datatypes.TodoTask
import com.egecius.gtdx.db.Db
import com.egecius.gtdx.utils.SorterImpl
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
        val taskToAdd = TodoTask(taskTitle, System.currentTimeMillis())
        db.addTask(taskToAdd)
    }

    private fun updateTasks(map: Map<String, Map<*, *>>) {
        val unsortedList = extractToTaskList(map)
        val sortedList = sortByMostRecent(unsortedList)
        view.onTasksUpdated(sortedList)
    }

    private fun sortByMostRecent(unsortedList: List<TodoTask>): List<TodoTask> {
        return SorterImpl().sortNewestFirst(unsortedList)
    }

    private fun extractToTaskList(map: Map<String, Map<*, *>>): List<TodoTask> {

        val list = ArrayList<TodoTask>()

        for ((key, taskMap) in map) {
            val title = taskMap["title"] as String
            val timestamp = taskMap["timestamp"] as Long
            val todoTask = TodoTask(title, timestamp)
            list.add(todoTask)
        }

        return list
    }

}
