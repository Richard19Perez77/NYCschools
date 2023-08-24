package com.practice.nycschools.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("jsonschema2pojo")
class SchoolClass {
    @SerializedName("dbn")
    @Expose
    var dbn: String = ""

    @JvmField
    @SerializedName("school_name")
    @Expose
    var schoolName: String = ""

    @JvmField
    @SerializedName("num_of_sat_test_takers")
    @Expose
    var numOfSatTestTakers: String = ""

    @JvmField
    @SerializedName("sat_critical_reading_avg_score")
    @Expose
    var satCriticalReadingAvgScore: String = ""

    @JvmField
    @SerializedName("sat_math_avg_score")
    @Expose
    var satMathAvgScore: String = ""

    @JvmField
    @SerializedName("sat_writing_avg_score")
    @Expose
    var satWritingAvgScore: String = ""
}