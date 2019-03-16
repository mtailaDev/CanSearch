package com.example.cansearch.search.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.cansearch.R
import com.example.cansearch.core.gone
import com.example.cansearch.core.visible
import com.example.cansearch.search.domain.DiseaseExtras
import com.example.cansearch.search.domain.SearchScreen
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.associated_disease_info_compound.*
import kotlinx.android.synthetic.main.fragment_trial.*
import kotlinx.android.synthetic.main.study_summary_compound.*
import kotlinx.android.synthetic.main.trial_detail_bottom_sheet.*
import kotlinx.android.synthetic.main.trials_summary_compound.*
import java.util.*


class TrialFragment : Fragment() {

    // todo - view model business logic

    private lateinit var sheetBehavior: BottomSheetBehavior<FrameLayout>
    private lateinit var parentViewModel: TrialActivityViewModel
    private lateinit var selectedTrial: SearchScreen.SearchResult
    private var fullDiseaseExtras = ArrayList<DiseaseExtras>()
    private var trimmedDiseaseExtras = emptyList<DiseaseExtras>()

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
        setTitle(selectedTrial.studySummary)
        showStudySummary(selectedTrial.studySummary)
        showTrialSummary(selectedTrial.trialSummary, selectedTrial.sites)
        showEligibilityCriteria(selectedTrial.eligibility)
        showDiseaseExtras(selectedTrial.associatedBiomarkers, selectedTrial.associatedDiseases)

        sheetBehavior = BottomSheetBehavior.from<FrameLayout>(bottom_sheet)
        setBottomSheetListener()
        setOnClickListeners()
    }

    private fun setTitle(studySummary: SearchScreen.SearchResult.StudySummary) {
        trial_name_value.text = studySummary.briefTitle
    }

    private fun showTrialSummary(
        trialSummary: SearchScreen.SearchResult.TrialSummary,
        sites: SearchScreen.SearchResult.Sites
    ) {
        trial_summary.setData(trialSummary)
    }

    private fun setBottomSheetListener() {
        sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {
            }

            override fun onStateChanged(p0: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_SETTLING,
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        background.visible()
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        background.gone()
                    }
                }
            }
        })
    }

    private fun setOnClickListeners() {
        study_summary_scientific_detail_btn.setOnClickListener {
            scientific_title.text = selectedTrial.studySummary.scientificTitle
            scientific_description.text = selectedTrial.studySummary.scientificDescription
            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        trial_summary_locations_btn.setOnClickListener {
            val parentActivity = activity as TrialActivity
            parentActivity.showLocation()
        }
        disease_extra_show_more.setOnClickListener {
            trial_associated_genes.clearChipGroup()
            if (trial_associated_genes.diseaseExtraExpanded) {
                trial_associated_genes.diseaseExtraExpanded = false
                trial_associated_genes.setData(trimmedDiseaseExtras)
                disease_extra_show_more.rotation = 90f
            } else {
                trial_associated_genes.diseaseExtraExpanded = true
                trial_associated_genes.setData(fullDiseaseExtras)
                disease_extra_show_more.rotation = -90f
            }
        }
    }

    private fun showDiseaseExtras(
        associatedBiomarkers: SearchScreen.SearchResult.AssociatedBiomarkers?,
        associatedDiseases: SearchScreen.SearchResult.AssociatedDiseases
    ) {
        val filteredArray = resources.getStringArray(com.example.cansearch.R.array.associateDiseaseFilter)
        var itemsToBeDeleted = mutableListOf<SearchScreen.SearchResult.AssociatedDiseases.Disease>()
        var filteredAssociatedDisease = associatedDiseases.associatedDiseases.toMutableList()
        filteredAssociatedDisease.forEach { disease ->
            filteredArray.forEach {
                if (disease.title.toLowerCase().contains(it)) {
                    itemsToBeDeleted.add(disease)
                }
            }
        }
        filteredAssociatedDisease.removeAll(itemsToBeDeleted)

        if (associatedBiomarkers?.biomarkers != null) {
            fullDiseaseExtras.addAll(associatedBiomarkers.biomarkers)
            fullDiseaseExtras.addAll(filteredAssociatedDisease)
            fullDiseaseExtras.shuffle()
            if (fullDiseaseExtras.size > 10) {
                trimmedDiseaseExtras = filteredAssociatedDisease.subList(0, 10)
                trial_associated_genes.setData(trimmedDiseaseExtras)
            } else {
                trial_associated_genes.setData(fullDiseaseExtras)
            }
        } else {
            if (associatedDiseases.associatedDiseases.isNotEmpty()) {
                fullDiseaseExtras.addAll(filteredAssociatedDisease)
                fullDiseaseExtras.shuffle()
                if (filteredAssociatedDisease.size > 10){
                    trimmedDiseaseExtras = filteredAssociatedDisease.subList(0, 10)
                    trial_associated_genes.setData(trimmedDiseaseExtras)
                } else {
                    trial_associated_genes.setData(fullDiseaseExtras)
                }
            } else {
                trial_associated_genes.gone()
            }
        }
    }

    private fun showEligibilityCriteria(eligibility: SearchScreen.SearchResult.EligibilityCriteria) {
        trial_eligibility.showData(eligibility)
    }

    private fun showStudySummary(studySummary: SearchScreen.SearchResult.StudySummary) {
        trial_study.setData(studySummary)
    }

    companion object {
        @JvmStatic
        fun newInstance(): TrialFragment {
            return TrialFragment()
        }
    }
}

