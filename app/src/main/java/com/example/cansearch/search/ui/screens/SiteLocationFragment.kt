package com.example.cansearch.search.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cansearch.R
import com.example.cansearch.search.domain.SearchScreen
import com.example.cansearch.search.domain.SiteHeader
import com.example.cansearch.search.domain.TrialSite
import com.example.cansearch.search.ui.adapters.LocationsAdapter
import kotlinx.android.synthetic.main.fragment_site_location.*


class SiteLocationFragment : Fragment() {

    private lateinit var parentViewModel: TrialActivityViewModel
    private lateinit var selectedTrial: SearchScreen.SearchResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity.let {
            parentViewModel = ViewModelProviders.of(it!!)[TrialActivityViewModel::class.java]
            selectedTrial = parentViewModel.selectedTrial.value!!
        }
    }

    private fun sortLocations() :MutableList<TrialSite>{
        val trialSiteList = mutableListOf<TrialSite>()

        val sortedList = selectedTrial.sites.locations.groupBy { it.orgCountry }.toList().sortedBy { it.first }
        sortedList.forEach { grouped ->
            trialSiteList.add(SiteHeader(grouped.first))
            trialSiteList.addAll(sortStates(grouped.second))
        }
        return trialSiteList
    }

    // todo - might be able to utilize comparator
    // todo - delegate to location.state <String?>
    private fun sortStates(test: List<SearchScreen.SearchResult.Sites.Location>): MutableList<SearchScreen.SearchResult.Sites.Location> {
        val trialSiteList = mutableListOf<SearchScreen.SearchResult.Sites.Location>()
        val groupedStates = test.groupBy { it.orgState }
        val sortedStates = groupedStates.toList().sortedBy { it.first }
        sortedStates.forEach {
            trialSiteList.addAll(it.second.sortedBy { it.orgCity })
        }
        return trialSiteList
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_site_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = LocationsAdapter(sortLocations())
        locations_rv.adapter = adapter
        locations_rv.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        @JvmStatic
        fun newInstance(): SiteLocationFragment {
            return SiteLocationFragment()
        }
    }
}