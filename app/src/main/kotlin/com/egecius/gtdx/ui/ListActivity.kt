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

class ListActivity : AppCompatActivity(), ListActivityView {

    private var enterTaskView: EditText? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: RecyclerViewAdapter? = null
    private var addBtn: Button? = null
    private var progressBar: ProgressBar? = null

    private val presenter = ListActivityPresenterImpl(this, DbImpl(FirebaseDatabase.getInstance()))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()

        presenter.onCreate()
    }

    override fun showProgressBar() {
        progressBar!!.visibility = VISIBLE
    }

    override fun hideProgressBar() {
        progressBar!!.visibility = INVISIBLE
    }

    private fun setupUi() {
        setContentView(R.layout.activity_main)
        findViews()
        setRecyclerView()
        setupAddButton()
    }

    private fun findViews() {
        enterTaskView = findViewById(R.id.enter_task) as EditText
        addBtn = findViewById(R.id.add_task) as Button
        recyclerView = findViewById(R.id.task_list) as RecyclerView
        progressBar = findViewById(R.id.progress_bar) as ProgressBar
    }

    private fun setRecyclerView() {
        adapter = RecyclerViewAdapter()
        recyclerView!!.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView!!.adapter = adapter
    }

    private fun setupAddButton() {
        addBtn!!.setOnClickListener {
            val taskTitle = enterTaskView!!.text.toString()
            presenter.onNewTaskAdded(taskTitle)
        }
    }

    override fun onTasksUpdated(list: List<TodoTask>) {
        adapter!!.updateList(list)
    }
}
