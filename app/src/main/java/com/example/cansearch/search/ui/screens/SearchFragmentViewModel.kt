package com.example.cansearch.search.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cansearch.core.data.get
import com.example.cansearch.core.data.success
import com.example.cansearch.search.domain.FetchSearchUseCase
import com.example.cansearch.search.domain.GetSearchUseCase
import com.example.cansearch.search.domain.SearchResultSummary
import com.example.cansearch.search.domain.SearchScreen
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchFragmentViewModel @Inject constructor(
    private val useCases: GetSearchUseCase,
    private val fetchSearchUseCase: FetchSearchUseCase
) : ViewModel() {

    val searchTotal = MutableLiveData<String>()
    val searchResults = MutableLiveData<List<SearchResultSummary>>()
    val searchResult = MutableLiveData<SearchScreen>()


    @SuppressLint("CheckResult")
    fun getSearch() {
        useCases.execute("Test")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it ->

                if (it.isSuccess()) {
                    searchResult.value = it.get()
                } else if (it.isFailure()){
                    Log.i("ASD", "ASDAS")
                    Log.i("ASD", "ASDAS")
                    Log.i("ASD", "ASDAS")
                }
            }

    }

}