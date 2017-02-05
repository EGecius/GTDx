package com.egecius.gtdx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.egecius.gtdx.datatypes.TodoTask;
import com.egecius.gtdx.db.DatabaseExploration;
import com.egecius.gtdx.db.Db;
import com.egecius.gtdx.db.DbImpl;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Subscriber;

public class ListActivity extends AppCompatActivity {

	private DatabaseExploration databaseExploration = new DatabaseExploration();

	private Db db = new DbImpl(FirebaseDatabase.getInstance());
	private EditText enterTaskView;
	private RecyclerView recyclerView;
	private RecyclerViewAdapter adapter;
	private Button addBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUi();

		observeTasksInCloudDatabase();
	}

	private void setupUi() {
		setContentView(R.layout.activity_main);
		findViews();
		setRecyclerView();
		setupAddButton();
	}

	private void setupAddButton() {
		addBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {
				addTaskToDb();
			}
		});
	}

	private void addTaskToDb() {
		String taskTitle = enterTaskView.getText().toString();
		TodoTask taskToAdd = new TodoTask(taskTitle);
		db.addTask(taskToAdd);
	}

	private void setRecyclerView() {
		adapter = new RecyclerViewAdapter();
		recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
		recyclerView.setAdapter(adapter);
	}

	private void findViews() {
		enterTaskView = (EditText) findViewById(R.id.enter_task);
		addBtn = (Button) findViewById(R.id.add_task);
		recyclerView = (RecyclerView) findViewById(R.id.task_list);
	}

	private void observeTasksInCloudDatabase() {
		db.getAllTasks().subscribe(new Subscriber<Map<String, Map>>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(final Throwable e) {

			}

			@Override
			public void onNext(final Map<String, Map> map) {
				List<TodoTask> list = extractToTaskList(map);
				adapter.updateList(list);
			}

		});
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
