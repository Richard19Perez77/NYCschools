package com.practice.nycschools.service

import com.practice.nycschools.model.NYCListClass
import com.practice.nycschools.model.SchoolClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestInterface {
    @GET("resource/s3k6-pzi2.json/")
    fun getNYCdata(): Call<List<NYCListClass>>

    @GET("resource/f9bf-2cp4.json")
    fun getSchoolData(@Query("dbn") dbn: String): Call<List<SchoolClass>>
}