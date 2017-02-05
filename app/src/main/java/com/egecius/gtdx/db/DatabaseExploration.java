package com.egecius.gtdx.db;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Top abstraction level for my database
 */
// TODO: 05/02/2017 to be removed later
public final class DatabaseExploration {

	private static final String KEY_MESSAGE = "message";
	private DatabaseReference dbRefRoot;
	private FirebaseDatabase db;

	public DatabaseExploration() {
		init();
	}

	private void init() {
		db = FirebaseDatabase.getInstance();
		dbRefRoot = db.getReference();
	}


	private void setListener(final DatabaseReference messagesRef) {
		messagesRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(final DataSnapshot dataSnapshot) {
				Log.i("Eg:DatabaseExploration:111", "onDataChange dataSnapshot " + dataSnapshot);
			}

			@Override
			public void onCancelled(final DatabaseError databaseError) {
				Log.e("Eg:DatabaseExploration:116", "onCancelled databaseError " + databaseError);
			}
		});
	}


	public void deleteMessage() {


		DatabaseReference messageRef = dbRefRoot.child("message");

		messageRef.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(final DataSnapshot dataSnapshot) {
				Log.i("Eg:DatabaseExploration:71", "onDataChange dataSnapshot.getValue() " + dataSnapshot.getValue());
			}

			@Override
			public void onCancelled(final DatabaseError databaseError) {
				Log.e("Eg:DatabaseExploration:76", "onCancelled databaseError " + databaseError);
			}
		});

		messageRef.removeValue();
	}

}
