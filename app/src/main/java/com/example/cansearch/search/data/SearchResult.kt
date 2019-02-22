package com.example.cansearch.search.data

import com.example.cansearch.search.ui.SearchListItem
import com.example.cansearch.trial.data.DetailedTrial
import com.example.cansearch.trial.ui.*
import com.google.gson.annotations.SerializedName

data class SearchResult(
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

        val eligibility: Eligibility,

        @SerializedName("number_of_arms")
        val numberOfArms: Int,

        val arms: List<Arms>

    ) {
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
            data class OrgCoordinates(val lon: Double, val lat: Double)
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

        // todo hard coded values
        fun mapToSearchListItem(): SearchListItem {
            return SearchListItem(
                briefTitle = briefTitle,
                principleInvestigator = principalInvestigator,
                leadOrganization = leadOrganization,
                phase = "Phase: " + phase.phase,
                totalSites = "${sites.size} locations"
            )
        }

        fun mapToDetailedTrial(): DetailedTrial {
            return DetailedTrial(
                studySummary = mapToStudySummary(),
                trialSummary = mapToTrialSummary(),
                associatedDiseases = mapToAssociatedDisease(),
                associatedGenes = mapToAssociatedGenes(),
                eligibility = mapToEligibility()
            )
        }

        fun mapToStudySummary(): StudySummary {
            return StudySummary(
                briefTitle = briefTitle,
                briefDescription = briefSummary,
                scientificTitle = officialTitle,
                scientificDescription = detailedSummary
            )
        }

        // todo hard coded values
         fun mapToTrialSummary(): TrialSummary {
            val trialSummaryList = mutableListOf<TrialSummaryItem>()
            trialSummaryList.add(TrialSummaryItem("Principle Investigator", principalInvestigator, false))
            trialSummaryList.add(TrialSummaryItem("Lead Organization", leadOrganization, false))
            trialSummaryList.add(TrialSummaryItem("Phase", phase.phase, true))
            trialSummaryList.add(TrialSummaryItem("Activity Status", trialStatus, true))
            trialSummaryList.add(TrialSummaryItem("Primary Purpose", primaryPurpose.phase, true))
            trialSummaryList.add(TrialSummaryItem("Anatomic Site", "Breast", false))
            return TrialSummary(trialSummaryList)
        }

        // todo hard coded values
         fun mapToAssociatedDisease(): AssociatedDiseases {
            val diseaseList = mutableListOf<String>()
            diseaseList.add("Breast Cancer")
            diseaseList.add("Malignant Neoplasm")
            diseaseList.add("Other Disease")
            diseaseList.add("Epithelial Neoplasm")
            diseaseList.add("Malignant Breast Neoplasm")
            diseaseList.add("HER2/Neu Status")
            diseaseList.add("Bilateral Breast Carcinoma")
            return AssociatedDiseases(diseaseList)
        }

        // todo hard coded values
         fun mapToAssociatedGenes(): AssociatedGenes {
            val list = mutableListOf<String>()
            list.add("HER2")
            list.add("P53")
            list.add("NF1")
            list.add("MAPK")
            list.add("BRAF")
            list.add("MERK")
            return AssociatedGenes(list)
        }

        // todo hard coded values
         fun mapToEligibility(): com.example.cansearch.trial.ui.Eligibility {
            val eligibilities = mutableListOf<TrialEligibilityItem>()
            eligibilities.add(TrialEligibilityItem("Gender", eligibility.structured.gender))
            eligibilities.add(TrialEligibilityItem("Minimum Age", "${eligibility.structured.minAgeInYears}"))
            eligibilities.add(TrialEligibilityItem("Max Age", "${eligibility.structured.maxAgeInYears}"))
            return com.example.cansearch.trial.ui.Eligibility(eligibilities)
        }
    }
}
