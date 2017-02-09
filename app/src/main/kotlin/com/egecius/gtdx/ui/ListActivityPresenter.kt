package com.egecius.gtdx.ui

internal interface ListActivityPresenter {

    fun onCreate()

    /** User has added a new task  */
    fun onNewTaskAdded(taskTitle: String)
}
