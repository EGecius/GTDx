package com.egecius.gtdx.ui.contexts

interface ContextsActivityView {
    fun showContextList(list: List<ContextItem>)
    fun hideProgressBar()
    fun showProgressBar()

}