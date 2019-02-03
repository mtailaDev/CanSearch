package com.example.cansearch.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cansearch.R
import com.example.cansearch.search.ui.QuickSearchAdapter
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupQuickSearchRecyclerView()
    }

    private fun setupQuickSearchRecyclerView() {
        val stringArray = resources.getStringArray(R.array.quickSearch)
        search_rv_quick_search.adapter = QuickSearchAdapter(stringArray.toList())
        search_rv_quick_search.layoutManager = LinearLayoutManager(context)
    }
}