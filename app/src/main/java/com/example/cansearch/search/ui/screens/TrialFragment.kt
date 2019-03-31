package com.example.cansearch.search.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.example.cansearch.R
import com.example.cansearch.core.gone
import com.example.cansearch.core.visible
import com.example.cansearch.search.di.SearchDagger
import com.example.cansearch.search.domain.SearchScreen.SearchResult.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.associated_disease_info_compound.*
import kotlinx.android.synthetic.main.fragment_trial.*
import kotlinx.android.synthetic.main.study_summary_compound.*
import kotlinx.android.synthetic.main.trial_detail_bottom_sheet.*
import kotlinx.android.synthetic.main.trials_summary_compound.*
import javax.inject.Inject


class TrialFragment : Fragment() {

    // nav args
    val args: TrialFragmentArgs by navArgs()

    // view models
    private lateinit var viewModel: TrialFragmentViewModel
    @Inject
    lateinit var viewModelFactory: TrialFragmentViewModelFactory

    // views
    private lateinit var sheetBehavior: BottomSheetBehavior<FrameLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SearchDagger.component.inject(this)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory)[TrialFragmentViewModel::class.java]
        viewModel.selectedTrialActivityID.value = args.studyId
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_trial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val changeBoundsTransition = ChangeBounds()
        changeBoundsTransition.duration = 200
        changeBoundsTransition.interpolator = AccelerateDecelerateInterpolator()

        val fadeTransition = Fade()
        changeBoundsTransition.duration = 200
        changeBoundsTransition.interpolator = AccelerateDecelerateInterpolator()
        TransitionManager.beginDelayedTransition(parent_container, changeBoundsTransition)
        TransitionManager.beginDelayedTransition(parent_container, fadeTransition)
        resetViews()

        viewModel.selectedTrial.observe(this, Observer {
            TransitionManager.beginDelayedTransition(parent_container, changeBoundsTransition)
            TransitionManager.beginDelayedTransition(parent_container, fadeTransition)
            setTitle(it.studySummary)
            showStudySummary(it.studySummary)
            showTrialSummary(it.trialSummary, it.sites)
            showEligibilityCriteria(it.eligibility)
            trial_associated_genes.setData(
                viewModel.filterAssociatedDiseaseInfo(
                    resources.getStringArray(com.example.cansearch.R.array.associateDiseaseFilter),
                    it.associatedDiseases,
                    it.associatedBiomarkers
                )
            )
            scientific_description.text = it.studySummary.scientificDescription
            sheetBehavior = BottomSheetBehavior.from<FrameLayout>(bottom_sheet)
            setBottomSheetListener()
            setOnClickListeners()
        })
        viewModel.getSearch()
    }

    private fun resetViews() {
        trial_name_value.text = ""
        trial_study.resetData()
    }

    private fun setTitle(studySummary: StudySummary) {
        trial_name_value.text = studySummary.briefTitle
    }

    private fun showTrialSummary(trialSummary: TrialSummary, sites: Sites) {
        trial_summary.setData(trialSummary)
    }

    private fun setBottomSheetListener() {
        sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, slideOffset: Float) {
            }

            @SuppressLint("SwitchIntDef")
            override fun onStateChanged(p0: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_SETTLING,
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        background.visible()
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        background.gone()
                    }
                    else -> {
                        // do nothing
                    }
                }
            }
        })
    }

    private fun setOnClickListeners() {
        study_summary_scientific_detail_btn.setOnClickListener {
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
                trial_associated_genes.setData(viewModel.getTrimmedDiseaseInfoList())
                disease_extra_show_more.rotation = 90f
            } else {
                trial_associated_genes.diseaseExtraExpanded = true
                trial_associated_genes.setData(viewModel.getFullDiseaseInfoList())
                disease_extra_show_more.rotation = -90f
            }
        }
        scientific_back_arrow.setOnClickListener {
            sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        trial_back.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }


    private fun showEligibilityCriteria(eligibility: EligibilityCriteria) {
        trial_eligibility.showData(eligibility)
    }

    private fun showStudySummary(studySummary: StudySummary) {
        trial_study.setData(studySummary)
    }
}

