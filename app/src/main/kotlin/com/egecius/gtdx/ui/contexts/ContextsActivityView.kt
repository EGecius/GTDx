package com.egecius.gtdx.ui.contexts

import com.egecius.gtdx.datatypes.ContextItem

internal interface ContextsActivityView {
    fun showContextList(list: List<ContextItem>)
    fun hideProgressBar()
    fun showProgressBar()

}