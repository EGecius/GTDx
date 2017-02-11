package com.egecius.gtdx.ui.contexts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.egecius.gtdx.R

/**
 * Shows a list of contexts
 */
class ContextsActivity : AppCompatActivity() {

    val presenter : ContextsActivityPresenter = ContextsActivityPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contexts)
    }
}
