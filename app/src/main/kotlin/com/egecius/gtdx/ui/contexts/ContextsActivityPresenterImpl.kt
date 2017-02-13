package com.egecius.gtdx.ui.contexts

import com.egecius.gtdx.db.Db

internal class ContextsActivityPresenterImpl(val view: ContextsActivityView, val db: Db) : ContextsActivityPresenter {

    override fun onCreate() {
        view.showProgressBar()
        db.getContextNames().subscribe { list ->
            view.hideProgressBar()
            view.showContextList(list)
        }
    }

}




