package com.egecius.gtdx.datatypes;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a task in a to-do list
 */
public final class TodoTask {

	@NonNull String id;
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

	public TodoTask(String id, final String title) {
		this.id = id;
		timestamp = System.currentTimeMillis();
		this.title = title;
	}

	public TodoTask(@NonNull final String id, @NonNull final String title, @NonNull final Long timestamp, final String
			context, final Long dueDate,
					final Integer repeatDays, final Long lenghtInMins, @NonNull final Integer priority, final String note,
					final Boolean isDone, final Boolean isStared) {
		this.id = id;
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

	@NonNull
	public String getId() {
		return id;
	}

	@NonNull
	public String getTitle() {
		return title;
	}

	@NonNull
	public Long getTimestamp() {
		return timestamp;
	}

	@Nullable
	public String getContext() {
		return context;
	}

	@Nullable
	public Long getDueDate() {
		return dueDate;
	}

	@Nullable
	public Integer getRepeatDays() {
		return repeatDays;
	}

	@Nullable
	public Long getLenghtInMins() {
		return lenghtInMins;
	}

	@NonNull
	public Integer getPriority() {
		return priority;
	}

	@Nullable
	public String getNote() {
		return note;
	}

	@Nullable
	public Boolean getDone() {
		return isDone;
	}

	@Nullable
	public Boolean getStared() {
		return isStared;
	}
}
