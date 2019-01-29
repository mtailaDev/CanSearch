package com.example.cansearch.login.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cansearch.login.OnboardingFragment

class OnboardingViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return OnboardingFragment.newInstance(OnboardingUI.Welcome)
            1 -> return OnboardingFragment.newInstance(OnboardingUI.Search)
            2 -> return OnboardingFragment.newInstance(OnboardingUI.Archive)
            3 -> return OnboardingFragment.newInstance(OnboardingUI.Contact)
            else -> return null
        }
    }

    companion object {
        private val NUM_ITEMS = 4
    }

}