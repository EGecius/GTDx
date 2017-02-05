package com.egecius.gtdx.datatypes;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Represents a task in a to-do list
 */
public final class TodoTask {

	/** Unix timestamp */
	@NonNull String title;
	/** Unix time of creation */
	@NonNull Long timestamp;
	@Nullable String context;
	/** Unix timestamp */
	@Nullable Long dueDate;
	/** How often, in days, the task wil repeat. Null of non-repeatable */
	@Nullable Integer repeatDays;
	/** Length of task is minutes */
	@Nullable Long lenghtInMins;
	/** Priority level of task from 0 to 4 */
	@NonNull Integer priority;
	/** Further details */
	@Nullable String note;
	/** Whether task is complete */
	@Nullable Boolean isDone;
	/** Whether user stared it */
	@Nullable Boolean isStared;

	public TodoTask() {
		// Default constructor required for calls to DataSnapshot.getValue(TodoTask.class)
	}

	public TodoTask(final String title) {
		timestamp = System.currentTimeMillis();
		this.title = title;
	}

	public TodoTask(@NonNull final String title, @NonNull final Long timestamp, final String context, final Long dueDate,
					final Integer repeatDays, final Long lenghtInMins, @NonNull final Integer priority, final String note,
					final Boolean isDone, final Boolean isStared) {
		this.title = title;
		this.timestamp = timestamp;
		this.context = context;
		this.dueDate = dueDate;
		this.repeatDays = repeatDays;
		this.lenghtInMins = lenghtInMins;
		this.priority = priority;
		this.note = note;
		this.isDone = isDone;
		this.isStared = isStared;
	}
}
