package com.example.cansearch.search.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cansearch.R
import com.example.cansearch.core.getColorCompat
import com.example.cansearch.search.ui.TrialSummaryItem
import kotlinx.android.synthetic.main.item_list_trial_summary.view.*


class TrialSummaryAdapter(private val trialSummaryList: List<TrialSummaryItem>) :
    RecyclerView.Adapter<TrialSummaryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val trialSummaryItemView = inflater.inflate(R.layout.item_list_trial_summary, parent, false)
        return ViewHolder(trialSummaryItemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.setSummaryItem(trialSummaryList[position])
    }

    override fun getItemCount(): Int {
        return trialSummaryList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setSummaryItem(trialSummaryItem: TrialSummaryItem) {
            itemView.trial_item_title.text = trialSummaryItem.summaryTitle
            itemView.trial_item_value.text = trialSummaryItem.summaryValue
            when (trialSummaryItem.summaryTitle) {
                itemView.context.getString(R.string.trial_summary_phase),
                itemView.context.getString(R.string.trial_summary_activity_status),
                itemView.context.getString(R.string.trial_summary_primary_purpose) -> {
                    itemView.trial_item_value.setTextColor(itemView.context.getColorCompat(R.color.title_text_tertiary))
                }
                else -> itemView.trial_item_value.setTextColor(itemView.context.getColorCompat(R.color.title_text_primary))
            }
        }
    }
}