package com.egecius.gtdx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.egecius.gtdx.datatypes.TodoTask;

import java.util.List;

public class ListActivity extends AppCompatActivity implements ListActivityView {

	private EditText enterTaskView;
	private RecyclerView recyclerView;
	private RecyclerViewAdapter adapter;
	private Button addBtn;

	private final ListActivityPresenter presenter = new ListActivityPresenterImpl(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUi();

		presenter.onCreate();
	}

	private void setupUi() {
		setContentView(R.layout.activity_main);
		findViews();
		setRecyclerView();
		setupAddButton();
	}

	private void findViews() {
		enterTaskView = (EditText) findViewById(R.id.enter_task);
		addBtn = (Button) findViewById(R.id.add_task);
		recyclerView = (RecyclerView) findViewById(R.id.task_list);
	}

	private void setRecyclerView() {
		adapter = new RecyclerViewAdapter();
		recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
		recyclerView.setAdapter(adapter);
	}

	private void setupAddButton() {
		addBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {
				String taskTitle = enterTaskView.getText().toString();
				presenter.onNewTaskAdded(taskTitle);
			}
		});
	}

	@Override
	public void onTasksUpdated(final List<TodoTask> list) {
		adapter.updateList(list);
	}
}
