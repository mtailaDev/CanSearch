package com.example.cansearch.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cansearch.R
import com.example.cansearch.core.gone
import com.example.cansearch.core.visible
import com.example.cansearch.search.ui.QuickSearchAdapter
import com.example.cansearch.search.ui.SearchResultsAdapter
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupQuickSearchRecyclerView()
        setOnClickListeners()
    }

    private fun setupQuickSearchRecyclerView() {
        val stringArray = resources.getStringArray(R.array.quickSearch)
        search_rv_quick_search.adapter = QuickSearchAdapter(stringArray.toList())
        search_rv_quick_search.layoutManager = LinearLayoutManager(context)
    }

    private fun setOnClickListeners() {
        search_iv_icon.setOnClickListener {
            if (!search_et_value.text.toString().isNullOrEmpty()) {
                showSearchingStatus()

            } else {
                showErrorMessage(it)
            }
        }
        search_btn.setOnClickListener {
            if (!search_et_value.text.toString().isNullOrEmpty()) {
                showSearchingStatus()
            } else {
                showErrorMessage(it)
            }
        }
    }

    private fun showSearchingStatus() {
        search_tv_results_title.text = resources.getString(R.string.search_status_searching)
        search_rv_quick_search.gone()
        search_btn.gone()
        search_lottie_searching.visible()
        search_lottie_searching.elevation = 3f
        // maybe a bit hacky - but use this to prevent
        search_lottie_searching.setOnTouchListener { v, event ->
            return@setOnTouchListener true
        }
        Single.just(Any())
            .delay(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                search_tv_results_title.text = resources.getString(R.string.search_status_results)
                search_lottie_searching.gone()
                search_rv_quick_search.visible()
                search_btn.visible()
                search_rv_quick_search.adapter =
                    SearchResultsAdapter(resources.getStringArray(R.array.quickSearch).toList())
                search_rv_quick_search.layoutManager = LinearLayoutManager(context)
            }
            .subscribe()

    }

    private fun showErrorMessage(view: View) {
        Snackbar.make(view, "Please provide a search value in the search box", Snackbar.LENGTH_SHORT).show()
    }

}