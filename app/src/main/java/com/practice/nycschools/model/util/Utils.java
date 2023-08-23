package com.practice.nycschools.model.util;


import com.practice.nycschools.model.SchoolClass;

public class Utils {

    public static String BuildSchoolString(SchoolClass school) {
        return "School: " + school.getSchoolName()
                + "\r\n"
                + "#Test Takers " + school.getNumOfSatTestTakers()
                + "\r\n"
                + "Avg SAT Reading: " + school.getSatCriticalReadingAvgScore()
                + "\r\n"
                + "Avg SAT Math: " + school.getSatMathAvgScore()
                + "\r\n"
                + "Avg SAT Writing: " + school.getSatWritingAvgScore();
    }
}
