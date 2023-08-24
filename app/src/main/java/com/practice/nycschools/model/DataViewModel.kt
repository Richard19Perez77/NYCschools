package com.practice.nycschools.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel : ViewModel() {

    var currentData: MutableLiveData<List<NYCListClass>>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
            }
            return field
        }
        private set

    var currentSchool: MutableLiveData<List<SchoolClass>>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
            }
            return field
        }
        private set
}