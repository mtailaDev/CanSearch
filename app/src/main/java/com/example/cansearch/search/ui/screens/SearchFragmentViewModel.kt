package com.example.cansearch.search.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.cansearch.search.domain.FetchSearchUseCase
import com.example.cansearch.search.domain.GetSearchUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchFragmentViewModel @Inject constructor(
    private val useCases: GetSearchUseCase,
    private val fetchSearchUseCase: FetchSearchUseCase
) : ViewModel() {

    fun getSearch() {
        useCases.execute("Test")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.i("ASDA", "ASDASD")
                Log.i("ASDA", "ASDASD")
                Log.i("ASDA", "ASDASD")
            }

    }

}