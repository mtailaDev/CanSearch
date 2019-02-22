package com.example.cansearch.trial.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cansearch.R
import com.example.cansearch.core.getColorCompat
import kotlinx.android.synthetic.main.item_list_trial_summary.view.*


class TrialSummaryAdapter(private val trialSummaryList: ArrayList<TrialSummaryItem>) : RecyclerView.Adapter<TrialSummaryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val quickSearchView = inflater.inflate(R.layout.item_list_trial_summary, parent, false)
        return ViewHolder(quickSearchView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.setSummaryItem(trialSummaryList[position])
    }

    override fun getItemCount(): Int {
        return trialSummaryList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setSummaryItem(quickSearchTitle: TrialSummaryItem) {
            itemView.trial_item_title.text = quickSearchTitle.summaryTitle
            itemView.trial_item_value.text = quickSearchTitle.summaryValue
            if (quickSearchTitle.highlight)
                itemView.trial_item_value.setTextColor(itemView.context.getColorCompat(R.color.title_text_tertiary))
            else
                itemView.trial_item_value.setTextColor(itemView.context.getColorCompat(R.color.title_text_primary))

        }
    }
}