package com.example.cansearch.search.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cansearch.R
import com.example.cansearch.search.ui.TrialEligibility
import com.example.cansearch.trial.ui.TrialEligibilityItem
import kotlinx.android.synthetic.main.item_list_eligibility.view.*


class TrialEligibilityAdapter(private val eligibilityList: List<TrialEligibilityItem>) : RecyclerView.Adapter<TrialEligibilityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val quickSearchView = inflater.inflate(R.layout.item_list_eligibility, parent, false)
        return ViewHolder(quickSearchView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.setSummaryItem(eligibilityList[position])
    }

    override fun getItemCount(): Int {
        return eligibilityList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setSummaryItem(eligibility: TrialEligibilityItem) {
            itemView.eligibility_title.text = eligibility.eligibilityTitle
            itemView.eligibility_value.text = eligibility.eligibilityValue
        }
    }
}