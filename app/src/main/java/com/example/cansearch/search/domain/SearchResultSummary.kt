package com.example.cansearch.search.domain

data class SearchResultSummary(
    val id: String,
    val briefTitle: String,
    val principleInvestigator: String,
    val leadOrganization: String,
    val phase: String,
    val totalSites: String
)