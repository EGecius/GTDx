package com.egecius.gtdx

internal interface ListActivityPresenter {

    fun onCreate()

    /** User has added a new task  */
    fun onNewTaskAdded(taskTitle: String)
}
