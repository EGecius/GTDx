package com.egecius.gtdx.ui.tasks

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.egecius.gtdx.R
import com.egecius.gtdx.datatypes.TodoTask
import com.egecius.gtdx.db.DbImpl
import com.egecius.gtdx.ui.contexts.ContextsActivity
import com.google.firebase.database.FirebaseDatabase
import com.jakewharton.rxbinding.view.RxView

class ListActivity : AppCompatActivity(), ListActivityView {

    private val enterTaskView by lazy { findViewById(R.id.enterTask) as EditText }
    private val tasksRecyclerView by lazy { findViewById(R.id.tasksRecyclerView) as RecyclerView }
    private val addTaskBtn by lazy { findViewById(R.id.addTaskBtn) as Button }
    private val progressBar by lazy { findViewById(R.id.progressBar) as ProgressBar }

    private val adapter: TasksRecyclerAdapter = TasksRecyclerAdapter()

    private val presenter: ListActivityPresenter = ListActivityPresenterImpl(this, DbImpl(FirebaseDatabase.getInstance()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()

        presenter.onCreate()
    }

    private fun setupUi() {
        setContentView(R.layout.activity_main)
        setRecyclerView()
        setupAddButton()
    }

    private fun setRecyclerView() {
        adapter.setRemoveTaskCallback({ id -> presenter.onRemoveTaskClicked(id) })
        tasksRecyclerView.adapter = adapter
        tasksRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
    }

    private fun setupAddButton() {
        RxView.clicks(addTaskBtn).subscribe {
            val taskTitle = enterTaskView.text.toString()
            presenter.onNewTaskAdded(taskTitle)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.context) {
            presenter.onContextBtnClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun goToContextsActivity() {
        val intent = Intent(this, ContextsActivity::class.java)
        startActivity(intent)
    }

    override fun showProgressBar() {
        progressBar.visibility = VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = INVISIBLE
    }

    override fun onTasksUpdated(list: List<TodoTask>) {
        adapter.updateList(list)
    }
}
