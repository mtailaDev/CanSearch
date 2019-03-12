package com.example.cansearch.search.ui.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cansearch.search.domain.SearchScreen
import com.example.cansearch.search.domain.SiteHeader
import com.example.cansearch.search.domain.TrialSite
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SiteLocationViewModel : ViewModel() {

    // todo - create base viewModel class to avoid calling onCleared everytime (see below)
    // todo - https://proandroiddev.com/managing-disposables-in-rxjava-2-the-less-bad-version-b3ff2b0b72a2

    private val compositeDisposable = CompositeDisposable()
    private var searchList = mutableListOf<SearchScreen.SearchResult.Sites.Location>()
    val trialList = MutableLiveData<List<TrialSite>>()
    val trimmedList = MutableLiveData<List<TrialSite>>()
    private lateinit var textWatcherObservable: Observable<String>

    override fun onCleared() {
        compositeDisposable.clear()
    }

    fun setObservable(observable: Observable<String>) {
        textWatcherObservable = observable
        compositeDisposable.add(textWatcherObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                if (!it.isNullOrEmpty()) {
                    val x = searchList.filter { searchList ->
                        searchList.orgCity?.toLowerCase()!!.contains(it.toLowerCase())
                                || searchList.orgCountry?.toLowerCase()!!.contains(it.toLowerCase())
                                || searchList.orgState?.toLowerCase()!!.contains(it.toLowerCase())
                    }
                    trimmedList.value = x
                } else {
                    trialList.value = trialList.value
                }
            }
            .subscribe()
        )

    }

    fun setInitialList(locations: List<SearchScreen.SearchResult.Sites.Location>) {
        sortLocations(locations)
    }

    private fun sortLocations(locations: List<SearchScreen.SearchResult.Sites.Location>) {
        val trialSiteList = mutableListOf<TrialSite>()

        val sortedList = locations.groupBy { it.orgCountry }.toList().sortedBy { it.first }
        sortedList.forEach { grouped ->
            trialSiteList.add(SiteHeader(grouped.first))
            trialSiteList.addAll(sortStates(grouped.second))
        }
        createSearchLocationList(trialSiteList)
        trialList.value = trialSiteList
    }

    private fun createSearchLocationList(trialSiteList: MutableList<TrialSite>) {
        trialSiteList.forEach {
            if (it is SearchScreen.SearchResult.Sites.Location) searchList.add(it)
        }
    }

    // todo - might be able to utilize comparator -delegate to location.state <String?>
    private fun sortStates(test: List<SearchScreen.SearchResult.Sites.Location>): MutableList<SearchScreen.SearchResult.Sites.Location> {
        val trialSiteList = mutableListOf<SearchScreen.SearchResult.Sites.Location>()
        val groupedStates = test.groupBy { it.orgState }
        val sortedStates = groupedStates.toList().sortedBy { it.first }
        sortedStates.forEach {
            trialSiteList.addAll(it.second.sortedBy { it.orgCity })
        }
        return trialSiteList
    }

}