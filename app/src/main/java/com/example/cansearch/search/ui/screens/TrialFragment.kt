package com.example.cansearch.search.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.cansearch.R
import com.example.cansearch.search.domain.SearchScreen
import com.example.cansearch.search.ui.TrialSummaryItem
import kotlinx.android.synthetic.main.fragment_trial.*

class TrialFragment : Fragment() {

//    private val trialSummaryList = ArrayList<TrialSummaryItem>()

    private lateinit var parentViewModel: TrialActivityViewModel
    private lateinit var selectedTrial: SearchScreen.SearchResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity.let {
            parentViewModel = ViewModelProviders.of(it!!)[TrialActivityViewModel::class.java]
            selectedTrial = parentViewModel.selectedTrial.value!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_trial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // todo - setup Associated Gene
        // todo - setup Assocaited Disease

        showStudySummary(selectedTrial.studySummary)
        showTrialSummary(selectedTrial.trialSummary)
        showEligibilityCriteria(selectedTrial.eligibility)
        showAssociatedDiseases(selectedTrial.associatedDiseases)
    }

    private fun showAssociatedDiseases(associatedDiseases: SearchScreen.SearchResult.AssociatedDiseases) {
        trial_disease.setData(associatedDiseases.associatedDiseases)
    }

    private fun showEligibilityCriteria(eligibility: SearchScreen.SearchResult.EligibilityCriteria) {
        trial_eligibility.showData(eligibility)
    }

    private fun showTrialSummary(trialSummary: SearchScreen.SearchResult.TrialSummary) {
        trial_summary.setData(trialSummary)
    }

    private fun showStudySummary(studySummary: SearchScreen.SearchResult.StudySummary) {
        trial_study.setData(studySummary)
    }

    private fun setupAssociatedGeneCardView() {
        trial_associated_genes.setCardTitle("Associated Gene Mutations")
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

//    private fun setupRecyclerView() {
//        trial_summary.setSummaryRecyclerView(generateStubData())
//    }

//    private fun generateStubData(): ArrayList<TrialSummaryItem> {
//        trialSummaryList.clear()
//        trialSummaryList.add(
//            TrialSummaryItem(
//                "Principle Investigator",
//                "Dr. Matt Taila",
//                false
//            )
//        )
//        trialSummaryList.add(TrialSummaryItem("Lead Organization", "TGen", false))
//        trialSummaryList.add(TrialSummaryItem("Phase", "III", true))
//        trialSummaryList.add(TrialSummaryItem("Activity Status", "Active", true))
//        trialSummaryList.add(TrialSummaryItem("Primary Purpose", "Treatment", true))
//        trialSummaryList.add(
//            TrialSummaryItem(
//                "Anatomic Site",
//                "Breast - female",
//                false
//            )
//        )
//        return trialSummaryList
//    }
}