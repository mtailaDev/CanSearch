package com.example.cansearch.search.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.cansearch.R
import com.example.cansearch.core.gone
import com.example.cansearch.search.domain.SearchScreen
import com.example.cansearch.search.ui.widgets.AssociatedDiseaseInfoCompoundView
import kotlinx.android.synthetic.main.fragment_trial.*

class TrialFragment : Fragment() {


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
        showStudySummary(selectedTrial.studySummary)
        showTrialSummary(selectedTrial.trialSummary)
        showEligibilityCriteria(selectedTrial.eligibility)
        showAssociatedDiseases(selectedTrial.associatedDiseases)
        showAssociatedBiomarkers(selectedTrial.associatedBiomarkers)
    }

    private fun showAssociatedBiomarkers(associatedBiomarkers: SearchScreen.SearchResult.AssociatedBiomarkers) {
        context.let {
            trial_associated_genes.setCardTitle(it!!.getString(R.string.trial_detail_associated_genes_title))
        }
        if (associatedBiomarkers.biomarkers != null){
            trial_associated_genes.setData(
                chipColor = R.color.chip_color_biomarkers,
                associatedData = associatedBiomarkers.biomarkers,
                chipType = AssociatedDiseaseInfoCompoundView.ChipType.BIOMARKERS)
        } else trial_associated_genes.gone()

    }

    private fun showAssociatedDiseases(associatedDiseases: SearchScreen.SearchResult.AssociatedDiseases) {
        context?.let {
            trial_disease.setCardTitle(it.getString(R.string.trial_detail_associated_disease_title))
            trial_disease.setData(
                chipColor = R.color.chip_color_disease,
                associatedData = associatedDiseases.associatedDiseases,
                chipType = AssociatedDiseaseInfoCompoundView.ChipType.DISEASE)
        }
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
}