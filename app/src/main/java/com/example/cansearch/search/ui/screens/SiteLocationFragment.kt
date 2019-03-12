package com.example.cansearch.search.ui.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cansearch.R
import com.example.cansearch.search.domain.SearchScreen
import com.example.cansearch.search.domain.TrialSite
import com.example.cansearch.search.ui.adapters.LocationsAdapter
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.fragment_site_location.*


class SiteLocationFragment : Fragment() {

    private lateinit var parentViewModel: TrialActivityViewModel
    private lateinit var siteLocationViewModel: SiteLocationViewModel
    private lateinit var selectedTrial: SearchScreen.SearchResult
    private lateinit var adapter: LocationsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity.let {
            parentViewModel = ViewModelProviders.of(it!!)[TrialActivityViewModel::class.java]
            selectedTrial = parentViewModel.selectedTrial.value!!
        }
        siteLocationViewModel = ViewModelProviders.of(this)[SiteLocationViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_site_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        siteLocationViewModel.setInitialList(selectedTrial.sites.locations)
        setObservers()
        siteLocationViewModel.setObservable(setSearchListener())

    }

    private fun setObservers() {
        siteLocationViewModel.trialList.observe(this, Observer {
            adapter = LocationsAdapter(it)
            locations_rv.adapter = adapter
            locations_rv.layoutManager = LinearLayoutManager(context)
            adapter.notifyDataSetChanged()
        })
        siteLocationViewModel.trimmedList.observe(this, Observer {
            adapter.sites = it
            adapter.notifyDataSetChanged()
        })
    }

    private fun setSearchListener(): Observable<String> {

        return Observable.create { subscriber ->
            locations_search_et.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(chars: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        subscriber.onNext(chars.toString())
                }
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): SiteLocationFragment {
            return SiteLocationFragment()
        }
    }
}