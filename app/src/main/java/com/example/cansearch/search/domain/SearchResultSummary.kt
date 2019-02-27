package com.example.cansearch.search.domain

import com.example.cansearch.App
import com.example.cansearch.R

data class SearchResultSummary(
    val id: String,
    val briefTitle: String,
    val principleInvestigator: String,
    val leadOrganization: String,
    val phase: String,
    val totalSites: String
) {
    companion object {
        fun mapFromSearchResult(searchResult: SearchScreen.SearchResult): SearchResultSummary =
            SearchResultSummary(
                id = searchResult.id,
                briefTitle = searchResult.studySummary.briefTitle,
                principleInvestigator = returnValue(searchResult, R.string.trial_summary_principle_investigator),
                leadOrganization = returnValue(searchResult, R.string.trial_summary_lead_organization),
                phase = returnValue(searchResult, R.string.trial_summary_phase),
                totalSites = "${searchResult.sites.locations.size} total sites"
            )

        private fun returnValue(searchResult: SearchScreen.SearchResult, stringId: Int) : String {
            return searchResult.trialSummary.summaryItems[App.instance.getString(stringId)]!!.second
        }
    }
}