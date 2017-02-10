package com.egecius.gtdx

import com.egecius.gtdx.db.DbImplTest
import com.egecius.gtdx.ui.ListActivityPresenterImplTest
import com.egecius.gtdx.utils.SorterImplTest

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        DbImplTest::class,
        ListActivityPresenterImplTest::class,
        SorterImplTest::class)
class AllUnitTestsSuite
