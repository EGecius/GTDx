package com.egecius.gtdx.feature.showcontexts

import com.egecius.gtdx.shared.db.Db
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner


/**
 * Tests for [ContextsActivityPresenterImpl]
 */
@RunWith(MockitoJUnitRunner::class)
class ContextsActivityPresenterImplTest {

    internal var presenter: ContextsActivityPresenterImpl? = null
    @Mock val db: Db? = null
    @Mock val view: ContextsActivityView? = null

    @Before
    fun setup() {
        presenter = ContextsActivityPresenterImpl(view!!, db!!)
    }

    @Test
    fun when_onCreate_then_showListOfContexts() {
        //WHEN
        presenter!!.onCreate()
        //THEN
        verify(db)!!.getContextNames()
    }

}