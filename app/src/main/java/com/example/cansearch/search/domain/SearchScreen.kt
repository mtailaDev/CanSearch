package com.example.cansearch.search.domain

import android.os.Parcelable
import com.example.cansearch.core.domain.RemoteError
import com.example.cansearch.core.domain.Result
import com.example.cansearch.core.domain.attemptTransform
import com.example.cansearch.search.data.SearchResultRaw
import kotlinx.android.parcel.Parcelize

data class SearchScreen(val totalResults: Int, val searchResults: List<SearchResult>) {

    @Parcelize
    data class SearchResult(
        val id: String,
        val studySummary: StudySummary,
        val trialSummary: TrialSummary,
        val associatedDiseases: AssociatedDiseases,
        val associatedBiomarkers: AssociatedBiomarkers,
        val eligibility: EligibilityCriteria
    ) : Parcelable {

        @Parcelize
        data class StudySummary(
            val briefTitle: String,
            val briefDescription: String,
            val scientificTitle: String,
            val scientificDescription: String
        ) : Parcelable

        @Parcelize
        data class AssociatedBiomarkers(
            val biomarkers: List<String>?
        ) : Parcelable

        @Parcelize
        data class TrialSummary(
            val summaryItems: LinkedHashMap<String, Pair<String, String>>
        ) : Parcelable

        @Parcelize
        data class AssociatedDiseases(
            val associatedDiseases: List<String>
        ) : Parcelable

        @Parcelize
        data class EligibilityCriteria(
            val eligibilityCriteria: LinkedHashMap<String, Pair<String, String>>
        ) : Parcelable
    }

    companion object {
        fun mapFromSearchResultRaw(rawResponse: SearchResultRaw): Result<RemoteError, SearchScreen> = attemptTransform {
            SearchScreen(
                totalResults = rawResponse.total,
                searchResults = rawResponse.trials.map { trials -> trials.mapToSearchResult() }
            )
        }
    }

}

