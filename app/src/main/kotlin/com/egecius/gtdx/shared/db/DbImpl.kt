package com.egecius.gtdx.shared.db

import com.egecius.gtdx.datatypes.TodoTask
import com.egecius.gtdx.datatypes.ContextItem
import com.google.firebase.database.*
import rx.Observable
import java.util.*

/**
 * Database implemented by Firebase
 */
class DbImpl(firebaseDatabase: FirebaseDatabase) : Db {

    private val refRoot: DatabaseReference = firebaseDatabase.reference
    private val refTasks: DatabaseReference
    private val refContexts: DatabaseReference

    init {
        refTasks = refRoot.child(TASKS)
        refContexts = refRoot.child(CONTEXTS)
    }

    override fun addTask(taskTitle: String) {
        val task = TodoTask(createId(), taskTitle, System.currentTimeMillis())
        refTasks.child(task.id).setValue(task)
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


    override fun getContextNames(): Observable<List<ContextItem>> {
        return Observable.create { subscriber ->

            refContexts.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    subscriber.onNext(extractContexts(dataSnapshot))
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    subscriber.onError(DbException(databaseError))
                }
            })
        }
    }

    private fun extractContexts(dataSnapshot: DataSnapshot): List<ContextItem> {
        val map = dataSnapshot.value as Map<String, Map<*, *>>

        val list = ArrayList<ContextItem>()

        for ((titleAsKey, contextMap) in map) {

            val taskIds = ArrayList<String>()

            val entriesIds = contextMap.entries.toList()

            for ((taskIdAsKey) in entriesIds) {
                taskIds.add(taskIdAsKey as String)
            }

            list.add(ContextItem(titleAsKey, taskIds))
        }

        return list
    }

    companion object {
        private val TASKS = "tasks"
        private val CONTEXTS = "contexts"
    }
}
