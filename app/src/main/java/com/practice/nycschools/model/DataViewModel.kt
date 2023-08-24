package com.practice.nycschools.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel : ViewModel() {

    var currentData: MutableLiveData<List<NYCListClass>> = MutableLiveData()
        private set

    var currentSchool: MutableLiveData<List<SchoolClass>> = MutableLiveData()
        private set
}