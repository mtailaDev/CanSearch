package com.example.cansearch.trial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cansearch.R
import kotlinx.android.synthetic.main.fragment_trial.*
import kotlinx.android.synthetic.main.trials_summary_compound.*

class TrialFragment : Fragment() {

    private val trialSummaryList = ArrayList<TrialSummaryItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_trial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        generateStubData()
    }

    private fun setupRecyclerView() {
        trial_summary.setSummaryRecyclerView(generateStubData())
    }

    private fun generateStubData(): ArrayList<TrialSummaryItem> {
        trialSummaryList.clear()
        trialSummaryList.add(TrialSummaryItem("Principle Investigator", "Dr. Matt Taila", false))
        trialSummaryList.add(TrialSummaryItem("Lead Organization", "TGen", false))
        trialSummaryList.add(TrialSummaryItem("Phase", "III", true))
        trialSummaryList.add(TrialSummaryItem("Activity Status", "Active", true))
        trialSummaryList.add(TrialSummaryItem("Primary Purpose", "Treatment", true))
        trialSummaryList.add(TrialSummaryItem("Anatomic Site", "Breast - female", false))
        return trialSummaryList
    }
}