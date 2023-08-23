package com.practice.nycschools.service;

import com.practice.nycschools.model.NYCListClass;
import com.practice.nycschools.model.SchoolClass;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestInterface {

    @GET("resource/s3k6-pzi2.json/")
    Call<List<NYCListClass>> GetNYCdata();

    @GET("resource/f9bf-2cp4.json")
    Call<List<SchoolClass>> GetSchoolData(@Query("dbn") String dbn);
}
