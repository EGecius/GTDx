package com.egecius.gtdx.db;

import com.egecius.gtdx.datatypes.TodoTask;

/**
 * Highest abstraction level to database
 */
public interface Db {
	void addTask(TodoTask task);
}
