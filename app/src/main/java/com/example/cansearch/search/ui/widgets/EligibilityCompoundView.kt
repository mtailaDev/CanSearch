package com.example.cansearch.search.ui.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cansearch.R
import com.example.cansearch.search.domain.SearchScreen
import com.example.cansearch.search.ui.adapters.TrialEligibilityAdapter
import com.example.cansearch.trial.ui.TrialEligibilityItem
import kotlinx.android.synthetic.main.eligibility_compound.view.*

class EligibilityCompoundView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.eligibility_compound, this)
    }

    fun showData(eligibility: SearchScreen.SearchResult.EligibilityCriteria) {
        val criteria = eligibility.eligibilityCriteria.map { TrialEligibilityItem(it.value.first, it.value.second) }
        setEligibilityRecyclerView(criteria)
    }

    private fun setEligibilityRecyclerView(criteria: List<TrialEligibilityItem>) {
        val eligibilityAdapter = TrialEligibilityAdapter(criteria)
        eligibility_recycler_view.adapter = eligibilityAdapter
        eligibility_recycler_view.layoutManager = LinearLayoutManager(context)
    }
}