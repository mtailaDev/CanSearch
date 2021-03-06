package com.example.cansearch.trial.data


import com.example.cansearch.trial.ui.TrialEligibilityItem
import com.example.cansearch.trial.ui.TrialSummaryItem

data class DetailedTrial(
    val studySummary: StudySummary,
    val trialSummary: TrialSummary,
    val associatedDiseases: AssociatedDiseases,
    val associatedGenes: AssociatedGenes,
    val eligibility: Eligibility
) {
    data class StudySummary(
        val briefTitle: String,
        val briefDescription: String,
        val scientificTitle: String,
        val scientificDescription: String
    )

    data class TrialSummary(
        val summaryItems: MutableList<TrialSummaryItem>
    )

    data class AssociatedDiseases(
        val associatedDiseases: MutableList<String>
    )

    data class AssociatedGenes(
        val associatedGenes: MutableList<String>
    )

    data class Eligibility(
        val eligibilityCriteria: MutableList<TrialEligibilityItem>
    )
}