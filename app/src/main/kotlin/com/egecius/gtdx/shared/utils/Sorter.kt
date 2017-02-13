package com.egecius.gtdx.shared.utils

import com.egecius.gtdx.datatypes.TodoTask

/**
 * Sorts collections
 */
interface Sorter {

    /** Returns sorted list of to-do tasks, with newest being at the top  */
    fun sortNewestFirst(unsorted: List<TodoTask>): List<TodoTask>

}
