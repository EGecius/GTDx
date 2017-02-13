package com.egecius.gtdx.shared.utils

import android.app.Activity
import android.content.Intent
import com.egecius.gtdx.feature.showtasks.TasksActivity
import java.util.*

/**
 *  Creates intents
 */
class IntentsCreator {

    fun createForTasksActivity(context: Activity, taskIds: List<String>): Intent {
        val intent = Intent(context, TasksActivity::class.java)
        intent.putStringArrayListExtra(KEY_TASK_IDS, ArrayList(taskIds))
        return intent
    }

    companion object {
        val KEY_TASK_IDS = "task_ids"
    }

}
