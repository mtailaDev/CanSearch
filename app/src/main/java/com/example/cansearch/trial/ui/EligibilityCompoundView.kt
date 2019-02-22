package com.example.cansearch.trial.ui

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cansearch.R
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

    private fun generateStubData(): List<TrialEligibilityItem> {
        val eligibilityList = mutableListOf<TrialEligibilityItem>()
        eligibilityList.add(TrialEligibilityItem("Sex", "Both"))
        eligibilityList.add(TrialEligibilityItem("Minimum Age", "18"))
        eligibilityList.add(TrialEligibilityItem("Maximum Age", "--"))
        return eligibilityList
    }
}