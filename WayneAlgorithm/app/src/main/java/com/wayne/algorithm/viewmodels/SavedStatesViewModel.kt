package com.wayne.algorithm.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SavedStatesViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    var savedState1:String?
    get() = savedStateHandle["test1"]?:""
    set(value) {
        savedStateHandle["test1"] = value
    }
}