package com.egecius.gtdx.feature.showcontexts

import com.egecius.gtdx.datatypes.ContextItem

internal interface ContextsActivityPresenter {
    fun onCreate()
    /** User clicked on a context in a list */
    fun onContextClicked(item: ContextItem)

}