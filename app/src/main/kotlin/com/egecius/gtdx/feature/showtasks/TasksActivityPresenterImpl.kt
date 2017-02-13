package com.egecius.gtdx.feature.showtasks

import com.egecius.gtdx.datatypes.TodoTask
import com.egecius.gtdx.shared.db.Db
import com.egecius.gtdx.shared.utils.SorterImpl
import java.util.*

internal class TasksActivityPresenterImpl(private val view: TasksActivityView, val db: Db) : TasksActivityPresenter {

    override fun onCreate(requestedTaskIds: List<String>) {
        if (isSpecificIdsRequested(requestedTaskIds)) {
            observeSpecificTasksTasksInCloudDatabase(requestedTaskIds)
        } else {
            observeAllTasksTasksInCloudDatabase()
        }
        view.showProgressBar()
    }

    private fun observeSpecificTasksTasksInCloudDatabase(ids: List<String>) {
        db.getTasks(ids)
    }

    fun isSpecificIdsRequested(requestedTaskIds: List<String>) = !requestedTaskIds.isEmpty()

    private fun observeAllTasksTasksInCloudDatabase() {
        db.getAllTasks.subscribe { map ->
            view.hideProgressBar()
            updateTasks(map)
        }
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
