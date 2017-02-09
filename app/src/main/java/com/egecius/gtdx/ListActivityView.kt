package com.egecius.gtdx

import com.egecius.gtdx.datatypes.TodoTask

internal interface ListActivityView {

    /** Tasks have been updated on the database  */
    fun onTasksUpdated(list: List<TodoTask>)
}
