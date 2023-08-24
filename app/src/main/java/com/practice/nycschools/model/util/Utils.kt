package com.practice.nycschools.model.util

import com.practice.nycschools.model.SchoolClass

object Utils {
    fun buildSchoolString(school: SchoolClass): String {
        return """
               School: ${school.schoolName}
               #Test Takers ${school.numOfSatTestTakers}
               Avg SAT Reading: ${school.satCriticalReadingAvgScore}
               Avg SAT Math: ${school.satMathAvgScore}
               Avg SAT Writing: ${school.satWritingAvgScore}
               """.trimIndent()
    }
}