package com.example.cansearch.search.domain

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
                principleInvestigator = "Dr Matt Taila",
                leadOrganization = "TGen",
                phase = "Phase",
                totalSites = "12312 total sites"
            )
    }

}