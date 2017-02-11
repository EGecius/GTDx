package com.egecius.gtdx.ui.contexts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.egecius.gtdx.R
import com.egecius.gtdx.db.DbImpl
import com.google.firebase.database.FirebaseDatabase

/**
 * Shows a list of contexts
 */
class ContextsActivity : AppCompatActivity(), ContextsActivityView {

    private val presenter : ContextsActivityPresenter = ContextsActivityPresenterImpl(this, DbImpl(FirebaseDatabase.getInstance()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contexts)

        presenter.onCreate()
    }

    override fun showContextList(list: List<String>) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

