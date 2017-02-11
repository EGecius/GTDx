package com.egecius.gtdx.ui.tasks

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
        db.getAllTasks.subscribe { map ->
            view.hideProgressBar()
            updateTasks(map) }
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
            val id = taskMap["id"] as String
            val title = taskMap["title"] as String
            val timestamp = taskMap["timestamp"] as Long
            val todoTask = TodoTask(id, title, timestamp)
            list.add(todoTask)
        }

        return list
    }

    override fun onNewTaskAdded(taskTitle: String) {
        addTaskToDb(taskTitle)
    }

    private fun addTaskToDb(taskTitle: String) {
        db.addTask(taskTitle)
    }

    override fun onRemoveTaskClicked(id: String) {
        db.removeTask(id)
    }

    override fun onContextBtnClicked() {
        view.goToContextsActivity()
    }

}
