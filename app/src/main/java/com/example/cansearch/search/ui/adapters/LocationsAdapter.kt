package com.example.cansearch.search.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cansearch.R
import com.example.cansearch.search.domain.SearchScreen
import com.example.cansearch.search.domain.SiteHeader
import com.example.cansearch.search.domain.TrialSite
import kotlinx.android.synthetic.main.item_list_country_header.view.*
import kotlinx.android.synthetic.main.item_list_location.view.*

class LocationsAdapter(var sites: List<TrialSite>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val HEADER = 0
        const val SITE = 1
    }

    override fun getItemViewType(position: Int): Int {
        val type = when (sites[position]) {
            is SiteHeader -> HEADER
            else -> SITE
        }
        return type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder: RecyclerView.ViewHolder = when (viewType) {
            HEADER -> {
                val headerView = inflater.inflate(R.layout.item_list_country_header, parent, false)
                HeaderViewHolder(headerView)
            }
            else -> {
                val locationView = inflater.inflate(R.layout.item_list_location, parent, false)
                LocationViewHolder(locationView)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (sites[position]) {
            is SiteHeader -> {
                val header = sites[position] as SiteHeader
                val holder = holder as HeaderViewHolder
                header.title?.let {
                    holder.setCountryHeader(it)
                }
            }
            else -> {
                val location = sites[position] as SearchScreen.SearchResult.Sites.Location
                val holder = holder as LocationViewHolder
                location?.let {
                    holder.setLocation(it)
                }
            }
        }
    }

    override fun getItemCount() = sites.size
}

class LocationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun setLocation(location: SearchScreen.SearchResult.Sites.Location) {
        itemView.location_title.text = "${location.orgState}, ${location.orgCity}"
        itemView.location_organization.text = location.orgName
    }
}

class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun setCountryHeader(countryTitle: String) {
        itemView.country_title.text = countryTitle
    }
}
