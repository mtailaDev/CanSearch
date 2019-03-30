package com.example.cansearch.search.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cansearch.R
import com.example.cansearch.core.gone
import com.example.cansearch.core.visible
import com.example.cansearch.search.di.SearchDagger
import com.example.cansearch.search.domain.SearchScreen
import com.example.cansearch.search.ui.adapters.QuickSearchAdapter
import com.example.cansearch.search.ui.adapters.SearchResultsAdapter
import com.example.cansearch.search.ui.model.QuickSearch
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : Fragment(), SearchResultsAdapter.OnTrialSelectedListener,
    QuickSearchAdapter.OnChipSelectedListener {

    private lateinit var viewModel: SearchFragmentViewModel
    @Inject
    lateinit var viewModelFactory: SearchFragmentViewModelFactory
    private val quickSearchList = mutableListOf<String>()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SearchDagger.component.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[SearchFragmentViewModel::class.java]
    }

    override fun onChipSelected(title: String) {
        quickSearchList.add(title)
        quickSearchList.forEach {
            Log.i("LIST", it)
        }
    }

    override fun onChipDeselected(title: String) {
        quickSearchList.remove(title)
        quickSearchList.forEach {
            Log.i("LIST", it)
        }
    }

    override fun onArchive(): Boolean {
        return true
    }

    override fun onTrialSelected(selectedTrial: SearchScreen.SearchResult) {
        this@SearchFragment.findNavController().navigate(R.id.action_searchFragment_to_trialFragment)
    }

    private fun showResultsRecyclerView(trials: List<SearchScreen.SearchResult>) {
        search_lottie_searching.gone()
        search_rv_quick_search.visible()
        search_rv_quick_search.adapter = SearchResultsAdapter(trials, this)
        search_rv_quick_search.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.example.cansearch.R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupQuickSearchRecyclerView()
        setOnClickListeners()
        viewModel.getSearch()
        viewModel.searchResult.observe(this, Observer {
            search_tv_results_title.text = "${it.totalResults} trials found"
            showResultsRecyclerView(it.searchResults)
        })
    }

    private fun setupQuickSearchRecyclerView() {
        val stringArray = resources.getStringArray(R.array.quickSearch)
        search_rv_quick_search.adapter = QuickSearchAdapter(QuickSearch(stringArray.toList()), this)
        search_rv_quick_search.layoutManager = LinearLayoutManager(context)
    }

    private fun setOnClickListeners() {
        search_btn.setOnClickListener {
            if (!search_et_value.text.toString().isNullOrEmpty()) {
//                showSearchingStatus()
            } else {
                showErrorMessage(it)
            }
        }
    }

    private fun showErrorMessage(view: View) {
        Snackbar.make(view, "Please provide a search value in the search box", Snackbar.LENGTH_SHORT).show()
    }

}