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
        var school = SchoolClass().apply {
            schoolName = "name"
            numOfSatTestTakers = "100"
            satCriticalReadingAvgScore = "50"
            satMathAvgScore = "50"
            satWritingAvgScore = "75"
        }

        var testBuilder = Utils.BuildSchoolString(school)

        var expected =
            "School: " + school.schoolName + "\r\n" + "#Test Takers " + school.numOfSatTestTakers + "\r\n" + "Avg SAT Reading: " + school.satCriticalReadingAvgScore + "\r\n" + "Avg SAT Math: " + school.satMathAvgScore + "\r\n" + "Avg SAT Writing: " + school.satWritingAvgScore

        assertEquals(expected, testBuilder)
    }
}