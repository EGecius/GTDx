package com.egecius.gtdx;

import com.egecius.gtdx.datatypes.TodoTask;

import java.util.List;

interface ListActivityView {

	/** Tasks have been updated on the database */
	void onTasksUpdated(List<TodoTask> list);
}
