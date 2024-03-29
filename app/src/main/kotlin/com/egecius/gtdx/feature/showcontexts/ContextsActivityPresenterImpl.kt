package com.egecius.gtdx.feature.showcontexts

import com.egecius.gtdx.datatypes.ContextItem
import com.egecius.gtdx.shared.db.Db

internal class ContextsActivityPresenterImpl(val view: ContextsActivityView, val db: Db) : ContextsActivityPresenter {

    override fun onCreate() {
        view.showProgressBar()
        db.getContextNames().subscribe { list ->
            view.hideProgressBar()
            view.showContextList(list)
        }
    }

    override fun onContextClicked(item: ContextItem) {
       view.gotToTasksActivity(item.idsList)
    }

}




