package com.practice.nycschools

import com.practice.nycschools.model.SchoolClass
import com.practice.nycschools.model.util.Utils
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun testStringBuilder() {
        val school = SchoolClass().apply {
            schoolName = "name"
            numOfSatTestTakers = "100"
            satCriticalReadingAvgScore = "50"
            satMathAvgScore = "50"
            satWritingAvgScore = "75"
        }

        val testBuilder = Utils.buildSchoolString(school)

        val expected = """
               School: ${school.schoolName}
               #Test Takers ${school.numOfSatTestTakers}
               Avg SAT Reading: ${school.satCriticalReadingAvgScore}
               Avg SAT Math: ${school.satMathAvgScore}
               Avg SAT Writing: ${school.satWritingAvgScore}
               """.trimIndent()

        assertEquals(expected, testBuilder)
    }
}