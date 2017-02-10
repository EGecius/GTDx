package com.egecius.gtdx.db

import com.egecius.gtdx.datatypes.TodoTask
import rx.Observable

/**
* Highest abstraction level to database
*/
interface Db {

    /** Adds a task to database  */
    fun addTask(taskTitle: String)

    /** Returns all tasks from the database  */
    val getAllTasks: Observable<Map<String, Map<*, *>>>
}
