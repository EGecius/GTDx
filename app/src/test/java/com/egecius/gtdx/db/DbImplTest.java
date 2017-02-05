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
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link DbImpl}
 */
@RunWith (MockitoJUnitRunner.class)
public class DbImplTest {

	public static final String TASKS = "tasks";
	public static final String KEY_PUSH_TASK = "key_push_task";
	Db db;

	@Mock FirebaseDatabase firebaseDb;
	@Mock DatabaseReference dbRefRoot;
	@Mock DatabaseReference dbRefTasks;
	@Mock DatabaseReference dbRefTasksPush;

	TodoTask task0 = new TodoTask();

	@Before
	public void setup() {
		
		given(firebaseDb.getReference()).willReturn(dbRefRoot);
		given(dbRefRoot.child(TASKS)).willReturn(dbRefTasks);
		given(dbRefTasks.push()).willReturn(dbRefTasksPush);
		given(dbRefTasksPush.getKey()).willReturn(KEY_PUSH_TASK);

		db = new DbImpl(firebaseDb);
	}

	@Test
	public void addsTasksSuccessfully() {
		//WHEN
		db.addTask(task0);
		//THEN
		// TODO: 05/02/2017 ideally I would be more specific here
		verify(dbRefRoot).updateChildren(anyMap());
	}

}