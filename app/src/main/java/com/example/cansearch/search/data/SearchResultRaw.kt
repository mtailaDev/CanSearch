package com.example.cansearch.search.data

import android.os.Parcelable
import com.example.cansearch.App
import com.example.cansearch.R
import com.example.cansearch.search.domain.SearchScreen
import com.example.cansearch.search.domain.SearchScreen.SearchResult
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class SearchResultRaw(
    val total: Int,
    val trials: List<Trials>
) {
    data class Trials(
        @SerializedName("nci_id")
        val nciID: String,

        @SerializedName("nct_id")
        val nctID: String,

        @SerializedName("outcome_measures")
        val outcomeMeasures: List<OutcomeMeasuresItem>,

        @SerializedName("current_trial_status")
        val trialStatus: String,

        @SerializedName("brief_title")
        val briefTitle: String,

        @SerializedName("official_title")
        val officialTitle: String,

        @SerializedName("brief_summary")
        val briefSummary: String,

        @SerializedName("detail_description")
        val detailedSummary: String,

        val phase: Phase,

        @SerializedName("primary_purpose")
        val primaryPurpose: PrimaryPurpose,

        val masking: Masking,

        @SerializedName("principal_investigator")
        val principalInvestigator: String,

        @SerializedName("lead_org")
        val leadOrganization: String,

        val collaborators: List<Collaborators>,

        val sites: List<SitesItem>,

        val diseases: List<AssociatedDisease>,

        val eligibility: Eligibility,

        @SerializedName("number_of_arms")
        val numberOfArms: Int,

        val arms: List<Arms>

    ) {

        data class AssociatedDisease(
            @SerializedName("display_name")
            val associatedDiseaseName: String
        )

        data class OutcomeMeasuresItem(
            @SerializedName("timeframe")
            val timeFrame: String,

            val name: String,

            val description: String,

            @SerializedName("type_code")
            val typeCode: String
        )

        data class Phase(val phase: String)

        data class PrimaryPurpose(@SerializedName("primary_purpose_code") val phase: String)

        data class Masking(
            val masking: String,

            @SerializedName("masking_allocation_code")
            val maskingCode: String
        )

        data class Collaborators(val name: String)

        data class SitesItem(
            @SerializedName("local_site_identifier")
            val localSiteIdentifier: String? = null,

            @SerializedName("org_state_or_province")
            val orgStateOrProvince: String? = null,

            @SerializedName("contact_name")
            val contactName: String? = null,

            @SerializedName("contact_phone")
            val contactPhone: String? = null,

            @SerializedName("org_address_line_2")
            val orgAddressLine2: Any? = null,


            @SerializedName("org_address_line_1")
            val orgAddressLine1: String? = null,

            @SerializedName("org_family")
            val orgFamily: Any? = null,

            @SerializedName("org_postal_code")
            val orgPostalCode: String? = null,

            @SerializedName("contact_email")
            val contactEmail: String? = null,

            @SerializedName("recruitment_status")
            val recruitmentStatus: String? = null,

            @SerializedName("org_city")
            val orgCity: String? = null,

            @SerializedName("org_email")
            val orgEmail: Any? = null,

            @SerializedName("org_country")
            val orgCountry: String? = null,

            @SerializedName("org_fax")
            val orgFax: Any? = null,

            @SerializedName("org_phone")
            val orgPhone: String? = null,

            @SerializedName("org_name")
            val orgName: String? = null,

            @SerializedName("org_coordinates")
            val orgCoordinates: OrgCoordinates
        ) {
            data class OrgCoordinates(
                val lon: Double,
                val lat: Double
            )
        }

        data class Eligibility(val structured: Structured) {

            data class Structured(
                @SerializedName("max_age_in_years")
                val maxAgeInYears: Int,

                @SerializedName("gender")
                val gender: String,

                @SerializedName("min_age_in_years")
                val minAgeInYears: Int
            )
        }

        data class Arms(
            val interventions: List<InterventionsItem>,

            @SerializedName("arm_type")
            val armType: String,

            @SerializedName("arm_name")
            val armName: String,

            @SerializedName("arm_description")
            val armDescription: String
        ) {
            data class InterventionsItem(
                @SerializedName("intervention_name")
                val interventionName: String
            )
        }

        fun mapToSearchResult(): SearchResult {
            return SearchResult(
                id = nciID,
                studySummary = mapToStudySummary(),
                trialSummary = mapToTrialSummary(),
                associatedDiseases = mapToAssociatedDisease(),
                associatedGenes = mapToAssociatedGenes(),
                eligibility = mapToEligibility()
            )
        }

        private fun mapToStudySummary(): SearchResult.StudySummary {
            return SearchResult.StudySummary(
                briefTitle = briefTitle,
                briefDescription = briefSummary,
                scientificTitle = officialTitle,
                scientificDescription = detailedSummary
            )
        }

        // todo hard coded values
        private fun mapToTrialSummary(): SearchResult.TrialSummary {
            val trialSummaryHashMap = LinkedHashMap<String, Pair<String, String>>()

            trialSummaryHashMap[App.instance.getString(R.string.trial_summary_principle_investigator)] =
                Pair(App.instance.getString(R.string.trial_summary_principle_investigator), "Dr. $principalInvestigator")

            trialSummaryHashMap[App.instance.getString(R.string.trial_summary_lead_organization)] =
                Pair(App.instance.getString(R.string.trial_summary_lead_organization), leadOrganization)

            trialSummaryHashMap[App.instance.getString(R.string.trial_summary_phase)] =
                Pair(App.instance.getString(R.string.trial_summary_phase), "Phase: ${phase.phase}")

            trialSummaryHashMap[App.instance.getString(R.string.trial_summary_activity_status)] =
                Pair(App.instance.getString(R.string.trial_summary_activity_status), trialStatus)

            trialSummaryHashMap[App.instance.getString(R.string.trial_summary_primary_purpose)] =
                Pair(App.instance.getString(R.string.trial_summary_primary_purpose), primaryPurpose.phase.toLowerCase().capitalize())

            // todo - add this raw model
            trialSummaryHashMap[App.instance.getString(R.string.trial_summary_anatomic_site)] =
                Pair(App.instance.getString(R.string.trial_summary_anatomic_site), "Lung")

            return SearchResult.TrialSummary(trialSummaryHashMap)
        }

        private fun mapToAssociatedDisease(): SearchResult.AssociatedDiseases {
            val diseaseList = diseases.map { "${it.associatedDiseaseName}" }
            return SearchResult.AssociatedDiseases(diseaseList)
        }

        // todo hard coded values
        private fun mapToAssociatedGenes(): SearchScreen.SearchResult.AssociatedGenes {
            val list = mutableListOf<String>()
            list.add("HER2")
            list.add("P53")
            list.add("NF1")
            list.add("MAPK")
            list.add("BRAF")
            list.add("MERK")
            return SearchResult.AssociatedGenes(list)
        }

        // todo hard coded values
        private fun mapToEligibility(): SearchResult.EligibilityCriteria {

            val trialEligibilityHashMap = LinkedHashMap<String, Pair<String, String>>()

            trialEligibilityHashMap[App.instance.getString(R.string.trial_eligibility_gender)] = Pair(App.instance.getString(R.string.trial_eligibility_gender), eligibility.structured.gender.toLowerCase().capitalize())

            trialEligibilityHashMap[App.instance.getString(R.string.trial_eligibility_min_age)] = Pair(App.instance.getString(R.string.trial_eligibility_min_age), "${eligibility.structured.minAgeInYears}")

            val maxAge = if (eligibility.structured.maxAgeInYears == 999) "NA" else "${eligibility.structured.maxAgeInYears}"

            trialEligibilityHashMap[App.instance.getString(R.string.trial_eligibility_max_age)] = Pair(App.instance.getString(R.string.trial_eligibility_max_age), maxAge)

            return SearchResult.EligibilityCriteria(trialEligibilityHashMap)
        }
    }
}
