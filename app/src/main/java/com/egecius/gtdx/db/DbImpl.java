package com.egecius.gtdx.db;

import android.util.Log;

import com.egecius.gtdx.datatypes.TodoTask;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Database implemented by Firebase
 */
public final class DbImpl implements Db {

	private static final String TASKS = "tasks";

	private final DatabaseReference dbRefRoot;

	public DbImpl(final FirebaseDatabase firebaseDatabase) {
		dbRefRoot = firebaseDatabase.getReference();
	}

	@Override
	public void addTask(final TodoTask task) {

		String key = dbRefRoot.child(TASKS).push().getKey();
		Map<String, Object> postValues = task.toMap();

		Map<String, Object> childUpdates = new HashMap<>();
		childUpdates.put("/" + TASKS + "/" + key, postValues);

		dbRefRoot.updateChildren(childUpdates);

		dbRefRoot.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(final DataSnapshot dataSnapshot) {
				Log.i("Eg:DbImpl:48", "onDataChange dataSnapshot.getValue() " + dataSnapshot.getValue());
			}

			@Override
			public void onCancelled(final DatabaseError databaseError) {
				Log.e("Eg:DbImpl:53", "onCancelled databaseError " + databaseError);
			}
		});
	}
}
