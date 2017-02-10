package com.egecius.gtdx.datatypes;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Represents a task in a to-do list
 */
public final class TodoTask implements Comparable<TodoTask> {

	/** Task id */
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

	public TodoTask(String id, final String title, Long timestamp) {
		this.id = id;
		this.title = title;
		this.timestamp = timestamp;
	}

	public TodoTask(@NonNull String id, @NonNull final String title, @NonNull final Long timestamp, final String context, final
	Long dueDate, final Integer repeatDays, final Long lenghtInMins, @NonNull final Integer priority, final String note, final Boolean isDone, final Boolean isStared) {
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

	@Override
	public String toString() {
		return "TodoTask{" +
				"title='" + title + '\'' +
				", timestamp=" + timestamp +
				", context='" + context + '\'' +
				", dueDate=" + dueDate +
				", repeatDays=" + repeatDays +
				", lenghtInMins=" + lenghtInMins +
				", priority=" + priority +
				", note='" + note + '\'' +
				", isDone=" + isDone +
				", isStared=" + isStared +
				'}';
	}

	@Override
	public int compareTo(@NonNull final TodoTask other) {

		if (timestamp < other.timestamp) {
			return -1;
		} else if (timestamp > other.timestamp) {
			return 1;
		}

		return 0;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (!(o instanceof TodoTask))
			return false;

		final TodoTask todoTask = (TodoTask) o;

		if (!title.equals(todoTask.title))
			return false;
		if (!timestamp.equals(todoTask.timestamp))
			return false;
		if (context != null ? !context.equals(todoTask.context) : todoTask.context != null)
			return false;
		if (dueDate != null ? !dueDate.equals(todoTask.dueDate) : todoTask.dueDate != null)
			return false;
		if (repeatDays != null ? !repeatDays.equals(todoTask.repeatDays) : todoTask.repeatDays != null)
			return false;
		if (lenghtInMins != null ? !lenghtInMins.equals(todoTask.lenghtInMins) : todoTask.lenghtInMins != null)
			return false;
		if (!priority.equals(todoTask.priority))
			return false;
		if (note != null ? !note.equals(todoTask.note) : todoTask.note != null)
			return false;
		if (isDone != null ? !isDone.equals(todoTask.isDone) : todoTask.isDone != null)
			return false;
		return isStared != null ? isStared.equals(todoTask.isStared) : todoTask.isStared == null;

	}

	@Override
	public int hashCode() {
		int result = title.hashCode();
		result = 31 * result + timestamp.hashCode();
		result = 31 * result + (context != null ? context.hashCode() : 0);
		result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
		result = 31 * result + (repeatDays != null ? repeatDays.hashCode() : 0);
		result = 31 * result + (lenghtInMins != null ? lenghtInMins.hashCode() : 0);
		result = 31 * result + priority.hashCode();
		result = 31 * result + (note != null ? note.hashCode() : 0);
		result = 31 * result + (isDone != null ? isDone.hashCode() : 0);
		result = 31 * result + (isStared != null ? isStared.hashCode() : 0);
		return result;
	}
}
