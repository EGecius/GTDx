package com.egecius.gtdx.feature.showcontexts

import com.egecius.gtdx.datatypes.ContextItem

 interface ContextsActivityView {
    fun showContextList(list: List<ContextItem>)
    fun hideProgressBar()
    fun showProgressBar()

}