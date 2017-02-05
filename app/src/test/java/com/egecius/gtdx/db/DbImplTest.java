package com.egecius.gtdx.db;


import com.egecius.gtdx.datatypes.TodoTask;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link DbImpl}
 */
@RunWith (MockitoJUnitRunner.class)
public class DbImplTest {

	private static final String TASKS = "tasks";
	public static final String KEY_PUSH_TASK = "key_push_task";
	private static final String TASK_TITLE_0 = "task_title_0";
	private static final String TASK_ID_0 = "task_id_0";

	TodoTask task0 = new TodoTask(TASK_ID_0, TASK_TITLE_0);

	@Mock FirebaseDatabase firebaseDb;
	@Mock DatabaseReference dbRefRoot;
	@Mock DatabaseReference dbRefTasks;
	@Mock DatabaseReference dbRefTasksChild0;


	Db db;

	@Before
	public void setup() {
		
		given(firebaseDb.getReference()).willReturn(dbRefRoot);
		given(dbRefRoot.child(TASKS)).willReturn(dbRefTasks);

		db = new DbImpl(firebaseDb);

		given(dbRefTasks.child(TASK_ID_0)).willReturn(dbRefTasksChild0);
	}

	@Test
	public void addsTasksSuccessfully() {
		//WHEN
		db.addTask(task0);
		//THEN
		verify(dbRefTasks).child(TASK_ID_0);
		verify(dbRefTasksChild0).setValue(task0);
	}

}