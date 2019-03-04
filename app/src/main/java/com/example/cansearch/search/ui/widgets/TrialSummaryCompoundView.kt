package com.example.cansearch.search.ui.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cansearch.R
import com.example.cansearch.search.domain.SearchScreen
import com.example.cansearch.search.ui.TrialSummaryItem
import com.example.cansearch.search.ui.adapters.TrialSummaryAdapter
import kotlinx.android.synthetic.main.trials_summary_compound.view.*

class TrialSummaryCompoundView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.trials_summary_compound, this)
    }

    private fun setSummaryRecyclerView(list: List<TrialSummaryItem>) {
        val trialSummaryAdapter = TrialSummaryAdapter(list)
        trial_summary_recycler_view.adapter = trialSummaryAdapter
        trial_summary_recycler_view.layoutManager = LinearLayoutManager(context)
    }

    fun setData(trialSummary: SearchScreen.SearchResult.TrialSummary, totalSites: Int) {
        val trialSummaryList = trialSummary.summaryItems.map { TrialSummaryItem(it.value.first, it.value.second) }
        setSummaryRecyclerView(trialSummaryList)
        setNumberOfLocations("$totalSites")
    }

    private fun setNumberOfLocations(totalSites: String) {
        trial_summary_locations_total.text = "$totalSites locations"
    }
}