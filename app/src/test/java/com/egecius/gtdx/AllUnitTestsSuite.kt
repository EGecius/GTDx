package com.egecius.gtdx

import com.egecius.gtdx.feature.showcontexts.ContextsActivityPresenterImplTest
import com.egecius.gtdx.shared.db.DbImplTest
import com.egecius.gtdx.ui.ListActivityPresenterImplTest
import com.egecius.gtdx.shared.utils.SorterImplTest

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        ContextsActivityPresenterImplTest::class,
        DbImplTest::class,
        ListActivityPresenterImplTest::class,
        SorterImplTest::class)
class AllUnitTestsSuite
