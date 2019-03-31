package com.example.cansearch.search.ui.screens

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cansearch.core.data.get
import com.example.cansearch.search.domain.DiseaseExtras
import com.example.cansearch.search.domain.FetchSearchUseCase
import com.example.cansearch.search.domain.GetSearchUseCase
import com.example.cansearch.search.domain.SearchScreen
import com.example.cansearch.search.domain.SearchScreen.SearchResult.AssociatedBiomarkers
import com.example.cansearch.search.domain.SearchScreen.SearchResult.AssociatedDiseases
import com.example.cansearch.search.domain.SearchScreen.SearchResult.AssociatedDiseases.Disease
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrialFragmentViewModel @Inject constructor(
    private val useCases: GetSearchUseCase,
    private val fetchSearchUseCase: FetchSearchUseCase
) : ViewModel() {

    val selectedTrialActivityID = MutableLiveData<String>()
    private val fullData = MutableLiveData<SearchScreen>()
    val selectedTrial = MutableLiveData<SearchScreen.SearchResult>()

    private var fullDiseaseExtra = ArrayList<DiseaseExtras>()
    private var previewDiseaseExtra = emptyList<DiseaseExtras>()

    @SuppressLint("CheckResult")
    fun getSearch() {
        useCases.execute("Test")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isSuccess()) {
                    fullData.value = it.get()
                    selectedTrial.value = fullData.value?.searchResults?.find { it.id == selectedTrialActivityID.value }
                } else if (it.isFailure()) {
                    // todo - handle errors
                }
            }
    }

    fun getFullDiseaseInfoList(): ArrayList<DiseaseExtras> {
        return fullDiseaseExtra
    }

    fun getTrimmedDiseaseInfoList(): List<DiseaseExtras> {
        return previewDiseaseExtra
    }

    fun filterAssociatedDiseaseInfo(
        filterList: Array<String>,
        associatedDiseases: AssociatedDiseases,
        associatedBiomarkers: AssociatedBiomarkers?
    ): List<DiseaseExtras> {
        fullDiseaseExtra.clear()
        val combinedList = ArrayList<DiseaseExtras>()

        associatedBiomarkers?.biomarkers?.let {
            combinedList.addAll(it)
        }
        combinedList.addAll(filterCommonDiseaseNames(filterList, associatedDiseases))
        combinedList.shuffle()

        fullDiseaseExtra = combinedList
        return if (combinedList.size > 10) {
            previewDiseaseExtra = combinedList.subList(0, 10)
            previewDiseaseExtra
        } else {
            fullDiseaseExtra
        }
    }

    private fun filterCommonDiseaseNames(
        filterList: Array<String>,
        associatedDiseases: AssociatedDiseases
    ): ArrayList<DiseaseExtras> {

        var diseasesToBeDeleted = mutableListOf<Disease>()
        val mutableDiseaseList = associatedDiseases.associatedDiseases.toMutableList()
        mutableDiseaseList.forEach { disease ->
            filterList.forEach {
                if (disease.title.toLowerCase().contains(it)) {
                    diseasesToBeDeleted.add(disease)
                }
            }
        }
        mutableDiseaseList.removeAll(diseasesToBeDeleted)

        val list = ArrayList<DiseaseExtras>()
        mutableDiseaseList.forEach {
            list.add(it)
        }
        return list
    }
}