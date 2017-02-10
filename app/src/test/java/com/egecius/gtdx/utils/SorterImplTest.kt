package com.egecius.gtdx.utils

import com.egecius.gtdx.datatypes.TodoTask
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import java.util.*


/**
 * Tests for [SorterImpl]
 */
@RunWith(MockitoJUnitRunner::class)
class SorterImplTest {

    internal var sorter: Sorter = SorterImpl()

    @Test
    fun sortsTasksFromNewestToOldest() {

        //create tasks
        val todoTask0 = TodoTask("task0", 1)
        val todoTask1 = TodoTask("task1", 2)
        val todoTask2 = TodoTask("task2", 3)
        val todoTask3 = TodoTask("task3", 4)

        //add to list
        val unsorted = ArrayList<TodoTask>()
        unsorted.add(todoTask3)
        unsorted.add(todoTask0)
        unsorted.add(todoTask2)
        unsorted.add(todoTask1)

        //WHEN
        val sorted = sorter.sortNewestFirst(unsorted)

        //THEN
        assertThat(sorted[0]).isEqualTo(todoTask3)
        assertThat(sorted[1]).isEqualTo(todoTask2)
        assertThat(sorted[2]).isEqualTo(todoTask1)
        assertThat(sorted[3]).isEqualTo(todoTask0)
    }

}