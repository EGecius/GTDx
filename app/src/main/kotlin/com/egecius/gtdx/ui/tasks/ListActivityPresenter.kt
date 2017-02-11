package com.egecius.gtdx.ui.tasks

internal interface ListActivityPresenter {

    fun onCreate()

    /** User has added a new task  */
    fun onNewTaskAdded(taskTitle: String)

    /**
     * @param id id of the task requested to be removed
     */
    fun onRemoveTaskClicked(id: String)

    /** User wants to to see list of contexts */
    fun onContextBtnClicked()

}
