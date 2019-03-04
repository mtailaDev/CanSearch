package com.example.cansearch.search.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cansearch.R
import kotlinx.android.synthetic.main.item_list_quick_search.view.*


class QuickSearchAdapter(private val quickSearchList: List<String>) : RecyclerView.Adapter<QuickSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val quickSearchView = inflater.inflate(R.layout.item_list_quick_search, parent, false)
        return ViewHolder(quickSearchView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.setQuickSearch(quickSearchList[position])
    }

    override fun getItemCount(): Int {
        return quickSearchList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setQuickSearch(quickSearchTitle: String) {
            itemView.quick_search_title.text = quickSearchTitle
        }
    }
}