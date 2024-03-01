package com.example.patched_kotest_robolectric_extension

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class KotestWithRoboExample : FunSpec({
    test("String length should return the length of the string") {
        "sammy".length shouldBe 5
        "".length shouldBe 0
    }
})
private class MockApplication : Application()

@RobolectricTest(sdk = Build.VERSION_CODES.O)
class ContainedRobolectricRunnerChangeApiLevelOTest : StringSpec({
    "Get the Build.VERSION_CODES.O" {
        Build.VERSION.SDK_INT shouldBe Build.VERSION_CODES.O
    }
})
@RobolectricTest(application = MockApplication::class)
abstract class ContainedRobolectricRunnerMergeTest : StringSpec()


// This test fails, before and after fix, don't know why
@RobolectricTest(sdk = Build.VERSION_CODES.O)
class ContainedRobolectricRunnerMergeApiVersionTest : ContainedRobolectricRunnerMergeTest() {
    init {
        "Get the Build.VERSION_CODES.O" {
            Build.VERSION.SDK_INT shouldBe Build.VERSION_CODES.O
        }

        "Get the Application defined in parent RobolectricTest annotation" {
            ApplicationProvider.getApplicationContext<Application>()::class shouldBe MockApplication::class
        }
    }
}

// This test passes now (doesn't throw any more), after patches to ContainedRobolectricRunner and RobolectricExtension
@RobolectricTest
class ApplicationServiceTests : FunSpec({
    test("start application service with non-activity shows entry state as closed") {
        // Given
        val context = ApplicationProvider.getApplicationContext<Context>()
    }
})
