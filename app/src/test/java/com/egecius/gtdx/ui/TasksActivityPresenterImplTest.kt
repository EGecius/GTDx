package com.egecius.gtdx.ui

import com.egecius.gtdx.datatypes.TodoTask
import com.egecius.gtdx.shared.db.Db
import com.egecius.gtdx.feature.showtasks.TasksActivityPresenterImpl
import com.egecius.gtdx.feature.showtasks.TasksActivityView
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner
import rx.Observable
import java.util.*


/**
 * Tests for [TasksActivityPresenterImpl]
 */
@RunWith(MockitoJUnitRunner::class)
class TasksActivityPresenterImplTest {

   internal var presenter: TasksActivityPresenterImpl? = null

    private val ID_1 = "id_1"
    private val ID_2 = "id_2"

    private val idsOfTasksRequested = listOf(ID_1, ID_2)
    private val tasksRequested = listOf(TodoTask(ID_1, "title_1", 1), TodoTask(ID_2, "title_2", 1))

    @Mock internal val view: TasksActivityView? = null
    @Mock internal var db: Db? = null

    @Before
    fun setup() {
        mockDb()
        presenter = TasksActivityPresenterImpl(view!!, db!!)
    }


    private fun mockDb() {
        val hashMap = HashMap<String, Map<String, String>>()
        given(db!!.getAllTasks).willReturn(Observable.just(hashMap))
        given(db!!.getTasks(idsOfTasksRequested)).willReturn(Observable.just(tasksRequested))
    }

    @Test
    fun when_onCreateCalledWithEmptyIdList_and_dbLoads_showsSpinnerWhileLoading_and_hidesOnceFinished() {
        //WHEN
       presenter!!.onCreate(listOf())
        //THEN
       verify(view!!).showProgressBar()
       verify(view).hideProgressBar()
    }

    @Test
    fun when_onContextBtnClicked_then_goesToListActivity() {
        //WHEN
        presenter!!.onContextBtnClicked()
        //THEN
        verify(view!!).goToContextsActivity()
    }

    @Test
    fun when_onCreateCalledWithIdList_then_db_requestedThatList() {
        //WHEN
        presenter!!.onCreate(idsOfTasksRequested)
        //THEN
        verify(db!!).getTasks(idsOfTasksRequested)
    }

    @Test
    fun when_onCreateCalledWithIdList_and_dbLoads_then_db_showsSpinnerWhileLoading_and_hidesOnceFinished() {
        //WHEN
        presenter!!.onCreate(idsOfTasksRequested)
        //THEN
        verify(view!!).showProgressBar()
        verify(view).hideProgressBar()
    }

    @Test
    fun when_onCreateCalledWithIdList_and_dbLoads_then_tasksArePassedToView() {
        //WHEN
        presenter!!.onCreate(idsOfTasksRequested)
        //THEN
        verify(view!!).showTasks(tasksRequested)
    }


}