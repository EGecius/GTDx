package com.egecius.gtdx.db;

import com.egecius.gtdx.datatypes.TodoTask;

import java.util.Map;

import rx.Observable;

/**
 * Highest abstraction level to database
 */
public interface Db {
	void addTask(TodoTask task);

	Observable<Map<String, Map>> getAllTasks();
}
