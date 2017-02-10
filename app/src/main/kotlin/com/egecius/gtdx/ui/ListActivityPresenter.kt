package com.egecius.gtdx.ui

internal interface ListActivityPresenter {

    fun onCreate()

    /** User has added a new task  */
    fun onNewTaskAdded(taskTitle: String)

    /**
     * @param id id of the task requested to be removed
     */
    fun onRemoveTaskClicked(id: String)

}
