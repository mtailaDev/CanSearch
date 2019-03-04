package com.example.cansearch.search.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cansearch.R

class SiteLocationFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_site_location, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(): SiteLocationFragment{
            return SiteLocationFragment()
        }
    }
}