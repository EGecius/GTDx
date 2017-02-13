package com.egecius.gtdx.feature.showcontexts

import com.egecius.gtdx.datatypes.ContextItem
import com.egecius.gtdx.shared.db.Db
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner
import rx.Observable


/**
 * Tests for [ContextsActivityPresenterImpl]
 */
@RunWith(MockitoJUnitRunner::class)
class ContextsActivityPresenterImplTest {

    internal var presenter: ContextsActivityPresenterImpl? = null
    @Mock val db: Db? = null
    @Mock val view: ContextsActivityView? = null

    private val taskIds = listOf("task_id_1", "task_id_2")
    private val contextItem = ContextItem("title", taskIds)

    @Before
    fun setup() {
        presenter = ContextsActivityPresenterImpl(view!!, db!!)
        given(db.getContextNames()).willReturn(Observable.just(listOf(contextItem)))
    }

    @Test
    fun when_onCreate_then_showListOfContexts() {
        //WHEN
        presenter!!.onCreate()
        //THEN
        verify(db)!!.getContextNames()
    }

    @Test
    fun when_onContextClicked_then_opensTaskActivityPassingTaskIds() {
        //WHEN
        presenter!!.onContextClicked(contextItem)
        //THEN
        verify(view)!!.gotToTasksActivity(taskIds)
    }

}