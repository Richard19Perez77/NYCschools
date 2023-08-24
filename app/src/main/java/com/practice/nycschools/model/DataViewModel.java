package com.practice.nycschools.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class DataViewModel extends ViewModel {

    // Create a LiveData with a String
    private MutableLiveData<List<NYCListClass>> currentData;

    public MutableLiveData<List<NYCListClass>> getCurrentData() {
        if (currentData == null) {
            currentData = new MutableLiveData<>();
        }
        return currentData;
    }

    private MutableLiveData<List<SchoolClass>> currentSchool;

    public MutableLiveData<List<SchoolClass>> getCurrentSchool() {
        if (currentSchool == null) {
            currentSchool = new MutableLiveData<>();
        }
        return currentSchool;
    }
}