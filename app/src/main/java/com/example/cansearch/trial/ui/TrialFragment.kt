package com.example.cansearch.trial.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cansearch.R
import kotlinx.android.synthetic.main.fragment_trial.*

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
        setupAssociatedDiseaseCardView()
        setupAssociatedGeneCardView()
    }

    private fun setupAssociatedGeneCardView() {
        trial_associated_genes.setCardTitle("Associated Gene Mutations")
        trial_associated_genes.setChipGroup(R.color.title_text_tertiary, generateGeneList())
    }

    private fun generateGeneList(): List<String> {
        val list = mutableListOf<String>()
        list.add("HER2")
        list.add("P53")
        list.add("NF1")
        list.add("MAPK")
        list.add("BRAF")
        list.add("MERK")
        return list
    }

    private fun setupAssociatedDiseaseCardView() {
        trial_disease.setCardTitle("Associated Diseases")
        trial_disease.setChipGroup(R.color.colorPrimary, generateDiseaseList())
    }

    private fun generateDiseaseList(): List<String> {
        val list = mutableListOf<String>()
        list.add("Breast Cancer")
        list.add("Malignant Neoplasm")
        list.add("Other Disease")
        list.add("Epithelial Neoplasm")
        list.add("Malignant Breast Neoplasm")
        list.add("HER2/Neu Status")
        list.add("Bilateral Breast Carcinoma")
        return list
    }

    private fun setupRecyclerView() {
        trial_summary.setSummaryRecyclerView(generateStubData())
    }

    private fun generateStubData(): ArrayList<TrialSummaryItem> {
        trialSummaryList.clear()
        trialSummaryList.add(
            TrialSummaryItem(
                "Principle Investigator",
                "Dr. Matt Taila",
                false
            )
        )
        trialSummaryList.add(TrialSummaryItem("Lead Organization", "TGen", false))
        trialSummaryList.add(TrialSummaryItem("Phase", "III", true))
        trialSummaryList.add(TrialSummaryItem("Activity Status", "Active", true))
        trialSummaryList.add(TrialSummaryItem("Primary Purpose", "Treatment", true))
        trialSummaryList.add(TrialSummaryItem("Anatomic Site", "Breast - female", false))
        return trialSummaryList
    }
}