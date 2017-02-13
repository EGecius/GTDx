package com.egecius.gtdx.feature.showcontexts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.egecius.gtdx.R
import com.egecius.gtdx.datatypes.ContextItem
import com.egecius.gtdx.shared.db.DbImpl
import com.google.firebase.database.FirebaseDatabase

/**
 * Shows a list of contexts
 */
class ContextsActivity : AppCompatActivity(), ContextsActivityView {

    private val tasksRecyclerView by lazy { findViewById(R.id.tasksRecyclerView) as RecyclerView }
    private val progressBar by lazy { findViewById(R.id.progressBar) as ProgressBar }

    private val adapter = ContextsRecyclerAdapter()

    private val presenter : ContextsActivityPresenter = ContextsActivityPresenterImpl(this, DbImpl(FirebaseDatabase.getInstance()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contexts)
        setRecyclerView()

        presenter.onCreate()
    }

    private fun setRecyclerView() {
        tasksRecyclerView.adapter = adapter
        tasksRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
    }
    override fun showContextList(list: List<ContextItem>) {
        adapter.updateList(list)
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

}

