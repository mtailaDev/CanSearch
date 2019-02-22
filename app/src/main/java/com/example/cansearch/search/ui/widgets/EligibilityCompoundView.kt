package com.example.cansearch.search.ui.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cansearch.R
import com.example.cansearch.search.ui.TrialEligibility
import com.example.cansearch.search.ui.adapters.TrialEligibilityAdapter
import kotlinx.android.synthetic.main.eligibility_compound.view.*

class EligibilityCompoundView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.eligibility_compound, this)
        setEligibilityRecyclerView()
    }

    fun setEligibilityRecyclerView() {
        // todo
//        val eligibilityAdapter = TrialEligibilityAdapter(list)
        val eligibilityAdapter = TrialEligibilityAdapter(generateStubData())
        eligibility_recycler_view.adapter = eligibilityAdapter
        eligibility_recycler_view.layoutManager = LinearLayoutManager(context)
    }

    private fun generateStubData(): List<TrialEligibility> {
        val eligibilityList = mutableListOf<TrialEligibility>()
        eligibilityList.add(TrialEligibility("Sex", "Both"))
        eligibilityList.add(TrialEligibility("Minimum Age", "18"))
        eligibilityList.add(TrialEligibility("Maximum Age", "--"))
        return eligibilityList
    }
}