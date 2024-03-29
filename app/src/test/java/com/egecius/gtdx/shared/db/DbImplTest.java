package com.egecius.gtdx.shared.db;


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

import static org.assertj.core.api.Assertions.assertThat;
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
	private static final String CONTEXTS = "contexts";

	private static final String TASK_TITLE_0 = "task_title_0";

	@Mock FirebaseDatabase firebaseDb;
	@Mock DatabaseReference refRoot;
	@Mock DatabaseReference refTasks;
	@Mock DatabaseReference refSingleTask;
	@Mock DatabaseReference refContexts;

	@Captor ArgumentCaptor<TodoTask> captor;

	@Captor ArgumentCaptor<ValueEventListener> captorValueEventListener;

	Db db;

	@Before

	public void setup() {
		given(firebaseDb.getReference()).willReturn(refRoot);
		given(refRoot.child(TASKS)).willReturn(refTasks);
		given(refRoot.child(CONTEXTS)).willReturn(refContexts);
		given(refTasks.child(anyString())).willReturn(refSingleTask);

		db = new DbImpl(firebaseDb);
	}

	@Test
	public void addsTasksSuccessfully() {
		//WHEN
		db.addTask(TASK_TITLE_0);
		//THEN
		verify(refSingleTask).setValue(captor.capture());
		String titleSet = captor.getValue().getTitle();
		assertThat(titleSet).isEqualTo(TASK_TITLE_0);
	}

}