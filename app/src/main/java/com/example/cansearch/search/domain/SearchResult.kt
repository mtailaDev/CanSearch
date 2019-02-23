package com.example.cansearch.search.domain

import com.example.cansearch.trial.ui.TrialEligibilityItem
import com.example.cansearch.trial.ui.TrialSummaryItem

data class SearchResult(
    val id: String,
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

    // this needs to be a key value map instead of a list
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

    private fun mapToSearchResultsSummary(): SearchResultSummary {
        return SearchResultSummary(
            id = id,
            briefTitle = studySummary.briefTitle,
            principleInvestigator = "Test",
            leadOrganization = "Test",
            phase = "Test",
            totalSites = "123123"
        )
    }
}