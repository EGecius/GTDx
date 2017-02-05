package com.egecius.gtdx.db;

import com.egecius.gtdx.datatypes.TodoTask;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.UUID;

import rx.Observable;
import rx.Subscriber;

/**
 * Database implemented by Firebase
 */
public final class DbImpl implements Db {

	private static final String TASKS = "tasks";
	/** last id used for a task */
	private static final String LAST_ID = "last_id";

	private final DatabaseReference dbRefRoot;
	private final DatabaseReference tasksRef;

	public DbImpl(final FirebaseDatabase firebaseDatabase) {
		dbRefRoot = firebaseDatabase.getReference();
		tasksRef = dbRefRoot.child(TASKS);
	}

	@Override
	public void addTask(final TodoTask task) {
		tasksRef.child(createId()).setValue(task);
	}

	private String createId() {
		return UUID.randomUUID().toString();
	}

	@Override
	public Observable<Map<String, Map>> getAllTasks() {
		return Observable.create(new Observable.OnSubscribe<Map<String, Map>>() {
			@Override
			public void call(final Subscriber<? super Map<String, Map>> subscriber) {
				tasksRef.addValueEventListener(new ValueEventListener() {
					@Override
					public void onDataChange(final DataSnapshot dataSnapshot) {
						//noinspection unchecked
						Map<String, Map> map = (Map<String, Map>) dataSnapshot.getValue();
						subscriber.onNext(map);
					}

					@Override
					public void onCancelled(final DatabaseError databaseError) {
						subscriber.onError(new DbExcpetion(databaseError));
					}
				});
			}
		});
	}
}
