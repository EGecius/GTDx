package com.egecius.gtdx.shared.db

import com.egecius.gtdx.datatypes.ContextItem
import rx.Observable

/**
 * Highest abstraction level to database
 */
interface Db {

    /** Adds a task to database  */
    fun addTask(taskTitle: String)

    /** Returns all tasks from the database  */
    val getAllTasks: Observable<Map<String, Map<*, *>>>

    /**
     * @param id id of the task to be removed
     */
    fun removeTask(id: String)

    /** GEts list of Contexts */
    fun getContextNames(): Observable<List<ContextItem>>
}
