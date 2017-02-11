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

    override fun addTask(taskTitle: String) {
        val task = TodoTask(createId(), taskTitle, System.currentTimeMillis())
        refTasks.child(task.id).setValue(task)
    }

    override fun getContexts(): Observable<List<String>> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    override fun removeTask(id: String) {
        refTasks.child(id).removeValue()
    }

    companion object {
        private val TASKS = "tasks"
    }
}
