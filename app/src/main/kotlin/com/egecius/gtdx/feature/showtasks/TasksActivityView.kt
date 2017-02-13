package com.egecius.gtdx.feature.showtasks

import com.egecius.gtdx.datatypes.TodoTask

internal interface TasksActivityView {

    /** Tasks have been updated on the database  */
    fun onTasksUpdated(list: List<TodoTask>)

    /** Shows progress indicator */
    fun showProgressBar()

    /** Hides progress indicator */
    fun hideProgressBar()

    fun goToContextsActivity()
}
