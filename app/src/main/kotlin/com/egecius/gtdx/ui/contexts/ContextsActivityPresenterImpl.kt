package com.egecius.gtdx.ui.contexts

import android.util.Log
import com.egecius.gtdx.db.Db

class ContextsActivityPresenterImpl(val view: ContextsActivityView, val db: Db) : ContextsActivityPresenter {

    override fun onCreate() {
        view.showProgressBar()
        db.getContextNames().subscribe { list ->
            view.hideProgressBar()
            view.showContextList(list)
        }
    }

}




