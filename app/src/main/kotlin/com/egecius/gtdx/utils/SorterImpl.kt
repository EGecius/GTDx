package com.egecius.gtdx.utils

import com.egecius.gtdx.datatypes.TodoTask
import java.util.*

/**
 * //todo
 */
internal class SorterImpl : Sorter {


    override fun sortNewestFirst(unsorted: List<TodoTask>): List<TodoTask> {
        val sorted = ArrayList(unsorted)
        Collections.sort(sorted, Collections.reverseOrder())
        return sorted
    }
}
