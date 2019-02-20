package com.example.cansearch.trial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cansearch.R
import kotlinx.android.synthetic.main.item_list_eligibility.view.*


class TrialEligibilityAdapter(private val eligibilityList: List<TrialEligibility>) : RecyclerView.Adapter<TrialEligibilityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrialEligibilityAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val quickSearchView = inflater.inflate(R.layout.item_list_eligibility, parent, false)
        return ViewHolder(quickSearchView)
    }

    override fun onBindViewHolder(viewHolder: TrialEligibilityAdapter.ViewHolder, position: Int) {
        viewHolder.setSummaryItem(eligibilityList[position])
    }

    override fun getItemCount(): Int {
        return eligibilityList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setSummaryItem(eligibility: TrialEligibility) {
            itemView.eligibility_title.text = eligibility.eligibilityTitle
            itemView.eligibility_value.text = eligibility.eligibilityValue
        }
    }
}