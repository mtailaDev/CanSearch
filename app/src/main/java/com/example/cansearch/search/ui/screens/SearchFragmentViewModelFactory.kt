package com.example.cansearch.search.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cansearch.search.domain.FetchSearchUseCase
import com.example.cansearch.search.domain.GetSearchUseCase
import javax.inject.Inject

class SearchFragmentViewModelFactory @Inject constructor(
    private val getSearchUseCases: GetSearchUseCase,
    private val fetchSearchUseCase: FetchSearchUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchFragmentViewModel(getSearchUseCases, fetchSearchUseCase) as T
    }
}