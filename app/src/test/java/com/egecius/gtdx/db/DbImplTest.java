package com.egecius.gtdx.db;


import com.egecius.gtdx.datatypes.TodoTask;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
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
	public static final String JSON_RECEIVED_FROM_FIREBASE = "json_received_from_firebase";
	private static final long TIMESTAMP_0 = 1L;

	TodoTask task0 = new TodoTask(TASK_TITLE_0, TIMESTAMP_0);

	@Mock FirebaseDatabase firebaseDb;
	@Mock DatabaseReference refRoot;
	@Mock DatabaseReference refTasks;
	@Mock DatabaseReference refSingleTask;

	@Captor ArgumentCaptor<ValueEventListener> captorValueEventListener;

	Db db;

	@Before

	public void setup() {

		given(firebaseDb.getReference()).willReturn(refRoot);
		given(refRoot.child(TASKS)).willReturn(refTasks);
		given(refTasks.child(anyString())).willReturn(refSingleTask);

		db = new DbImpl(firebaseDb);
	}

	@Test
	public void addsTasksSuccessfully() {
		//WHEN
		db.addTask(task0);
		//THEN
		verify(refSingleTask).setValue(task0);
	}

}