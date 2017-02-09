package com.egecius.gtdx.db

import com.egecius.gtdx.datatypes.TodoTask
import rx.Observable

/**
* Highest abstraction level to database
*/
interface Db {

    /** Adds a task to database  */
    fun addTask(task: TodoTask)

    /** Returns all tasks from the database  */
    val allTasks: Observable<Map<String, Map<*, *>>>
}
