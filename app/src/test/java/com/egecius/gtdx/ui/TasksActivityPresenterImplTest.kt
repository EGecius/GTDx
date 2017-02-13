package com.egecius.gtdx.ui

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
    }

    @Test
    fun when_onCreateCalledAndDbLoads_showsSpinnerWhileLoading_and_hidesOnceFinished() {
        //WHEN
       presenter!!.onCreate()
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

}