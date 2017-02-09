package com.egecius.gtdx

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.EditText

import com.egecius.gtdx.datatypes.TodoTask

class ListActivity : AppCompatActivity(), ListActivityView {

    private var enterTaskView: EditText? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: RecyclerViewAdapter? = null
    private var addBtn: Button? = null

    private val presenter = ListActivityPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()

        presenter.onCreate()
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
