package com.example.cansearch.search.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cansearch.R

class SearchResultsAdapter(val quickSearchList: List<String>) : RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val quickSearchView = inflater.inflate(R.layout.item_list_search, parent, false)
        return ViewHolder(quickSearchView)
    }

    override fun onBindViewHolder(viewHolder: SearchResultsAdapter.ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return quickSearchList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}