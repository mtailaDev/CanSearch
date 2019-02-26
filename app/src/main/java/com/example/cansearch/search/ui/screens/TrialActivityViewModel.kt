package com.example.cansearch.search.ui.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cansearch.search.domain.SearchScreen

class TrialActivityViewModel : ViewModel(){

    val selectedTrial = MutableLiveData<SearchScreen.SearchResult>()
}