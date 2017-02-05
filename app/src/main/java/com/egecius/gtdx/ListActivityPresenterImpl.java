package com.egecius.gtdx;

import com.egecius.gtdx.datatypes.TodoTask;
import com.egecius.gtdx.db.Db;
import com.egecius.gtdx.db.DbImpl;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.functions.Action1;

final class ListActivityPresenterImpl implements ListActivityPresenter {

	private Db db = new DbImpl(FirebaseDatabase.getInstance());
	private final ListActivityView listActivity;

	ListActivityPresenterImpl(final ListActivityView listActivity) {
		this.listActivity = listActivity;
	}

	@Override
	public void onCreate() {
		observeTasksInCloudDatabase();
	}

	private void observeTasksInCloudDatabase() {
		db.getAllTasks().subscribe(new Action1<Map<String, Map>>() {
			@Override
			public void call(final Map<String, Map> map) {
				updateTasks(map);
			}
		});
	}

	@Override
	public void onNewTaskAdded(final String taskTitle) {
		addTaskToDb(taskTitle);
	}

	private void addTaskToDb(final String taskTitle) {
		TodoTask taskToAdd = new TodoTask(taskTitle);
		db.addTask(taskToAdd);
	}

	private void updateTasks(final Map<String, Map> map) {
		List<TodoTask> list = extractToTaskList(map);
		listActivity.onTasksUpdated(list);
	}

	private List<TodoTask> extractToTaskList(final Map<String, Map> map) {

		List<TodoTask> list = new ArrayList<>();

		for (Map.Entry<String, Map> entry : map.entrySet()) {
			Map taskMap = entry.getValue();
			String title = (String) taskMap.get("title");
			TodoTask todoTask = new TodoTask(title);
			list.add(todoTask);
		}

		return list;
	}

}
