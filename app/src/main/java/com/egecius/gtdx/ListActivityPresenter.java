package com.egecius.gtdx;

interface ListActivityPresenter {

	void onCreate();

	/** User has added a new task */
	void onNewTaskAdded(String taskTitle);
}
