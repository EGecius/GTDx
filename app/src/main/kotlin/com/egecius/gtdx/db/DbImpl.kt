package com.egecius.gtdx.db

import com.egecius.gtdx.datatypes.TodoTask
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.UUID

import rx.Observable
import rx.Subscriber

/**
 * Database implemented by Firebase
 */
class DbImpl(firebaseDatabase: FirebaseDatabase) : Db {

    private val refRoot: DatabaseReference = firebaseDatabase.reference
    private val refTasks: DatabaseReference

    init {
        refTasks = refRoot.child(TASKS)
    }

    override fun addTask(task: TodoTask) {
        refTasks.child(createId()).setValue(task)
    }

    private fun createId(): String {
        return UUID.randomUUID().toString()
    }

    override val getAllTasks: Observable<Map<String, Map<*, *>>>
        get() = Observable.create { subscriber ->
            refTasks.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val map = dataSnapshot.value as Map<String, Map<*, *>>
                    subscriber.onNext(map)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    subscriber.onError(DbException(databaseError))
                }
            })
        }

    companion object {

        private val TASKS = "tasks"
        /** last id used for a task  */
        private val LAST_ID = "last_id"
    }
}
