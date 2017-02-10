package com.egecius.gtdx.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.egecius.gtdx.R
import com.egecius.gtdx.datatypes.TodoTask
import com.egecius.gtdx.db.DbImpl
import com.google.firebase.database.FirebaseDatabase
import com.jakewharton.rxbinding.view.RxView

class ListActivity : AppCompatActivity(), ListActivityView {

    private val enterTaskView by lazy { findViewById(R.id.enterTask) as EditText }
    private val tasksRecyclerView by lazy { findViewById(R.id.tasksRecyclerView) as RecyclerView }
    private val addTaskBtn by lazy { findViewById(R.id.addTaskBtn) as Button }
    private val progressBar by lazy { findViewById(R.id.progressBar) as ProgressBar }

    private val adapter: RecyclerViewAdapter = RecyclerViewAdapter()

    private val presenter = ListActivityPresenterImpl(this, DbImpl(FirebaseDatabase.getInstance()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()

        presenter.onCreate()
    }

    override fun showProgressBar() {
        progressBar.visibility = VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = INVISIBLE
    }

    private fun setupUi() {
        setContentView(R.layout.activity_main)
        setRecyclerView()
        setupAddButton()
    }

    private fun setRecyclerView() {
        tasksRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        tasksRecyclerView.adapter = adapter
    }

    private fun setupAddButton() {
        RxView.clicks(addTaskBtn).subscribe {
            val taskTitle = enterTaskView.text.toString()
            presenter.onNewTaskAdded(taskTitle)
        }
    }

    override fun onTasksUpdated(list: List<TodoTask>) {
        adapter.updateList(list)
    }
}
