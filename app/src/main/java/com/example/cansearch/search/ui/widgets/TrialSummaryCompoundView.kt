package com.example.cansearch.search.ui.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cansearch.R
import com.example.cansearch.search.ui.TrialSummaryItem
import com.example.cansearch.search.ui.adapters.TrialSummaryAdapter
import kotlinx.android.synthetic.main.trials_summary_compound.view.*

class TrialSummaryCompoundView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.trials_summary_compound, this)
    }

    fun setSummaryRecyclerView(list: ArrayList<TrialSummaryItem>) {
        val trialSummaryAdapter = TrialSummaryAdapter(list)
        trial_summary_recycler_view.adapter = trialSummaryAdapter
        trial_summary_recycler_view.layoutManager = LinearLayoutManager(context)
    }
}