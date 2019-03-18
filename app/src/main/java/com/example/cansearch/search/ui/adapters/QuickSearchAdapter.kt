package com.example.cansearch.search.ui.adapters


import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cansearch.R
import com.example.cansearch.core.getColorCompat
import com.example.cansearch.search.ui.model.QuickSearch
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.item_list_quick_search.view.*


class QuickSearchAdapter(
    private val quickSearch: QuickSearch,
    private val chipSelectedListener: OnChipSelectedListener
) : RecyclerView.Adapter<QuickSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val quickSearchView = inflater.inflate(R.layout.item_list_quick_search, parent, false)
        return ViewHolder(quickSearchView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.setQuickSearch(quickSearch.quickSearchList, chipSelectedListener)
    }

    override fun getItemCount(): Int {
        return 1
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setQuickSearch(quickSearchList: List<String>, chipSelectedListener: OnChipSelectedListener) {

            quickSearchList.forEach {
                val chip = Chip(itemView.context)
                chip.chipStrokeWidth = 2f
                chip.setChipBackgroundColorResource(R.color.chip_color_genes_background)
                chip.chipStrokeColor =
                    ColorStateList.valueOf(itemView.context.getColorCompat(R.color.chip_color_disease_stroke))
                chip.setTextColor(itemView.context.getColorCompat(R.color.chip_color_gene_stroke))
                chip.isCheckable = true
                chip.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) chipSelectedListener.onChipSelected(buttonView.text.toString())
                    else chipSelectedListener.onChipDeselected(buttonView.text.toString())
                }
                chip.text = it
                itemView.quickSearchChipGroup.addView(chip)
            }
        }
    }

    interface OnChipSelectedListener {
        fun onChipSelected(title: String)
        fun onChipDeselected(title: String)
    }
}