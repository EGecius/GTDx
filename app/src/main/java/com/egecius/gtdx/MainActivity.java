package com.egecius.gtdx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.egecius.gtdx.datatypes.TodoTask;
import com.egecius.gtdx.db.DatabaseExploration;
import com.egecius.gtdx.db.Db;
import com.egecius.gtdx.db.DbImpl;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

	private DatabaseExploration databaseExploration = new DatabaseExploration();

	private Db db = new DbImpl(FirebaseDatabase.getInstance());

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		db.addTask(new TodoTask("id2", "egis task 2"));
	}

}
