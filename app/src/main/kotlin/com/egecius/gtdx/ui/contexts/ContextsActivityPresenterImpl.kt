package com.egecius.gtdx.ui.contexts

import com.egecius.gtdx.db.Db

class ContextsActivityPresenterImpl(val view: ContextsActivityView, val db: Db) : ContextsActivityPresenter {
    
    override fun onCreate() {
        db.getContexts()
    }

}




