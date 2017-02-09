package com.egecius.gtdx.utils;

import com.egecius.gtdx.datatypes.TodoTask;

import java.util.List;

/**
 * Sorts collections
 */
public interface Sorter {

	/** Returns sorted list of to-do tasks, with newest being at the top */
	List<TodoTask> sortNewestFirst(List<TodoTask> unsorted);

}
