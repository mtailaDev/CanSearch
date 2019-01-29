package com.example.cansearch.login.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cansearch.R
import com.example.cansearch.login.OnboardingFragment

class OnboardingViewPagerAdapter(val fragmentManager: FragmentManager, val context: Context) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return OnboardingFragment.newInstance(
                OnboardingUI(
                    R.drawable.background_welcome_gradient,
                    context.getString(R.string.onboarding_welcome_title),
                    context.getString(R.string.onboarding_welcome_description)
                )
            )
            1 -> return OnboardingFragment.newInstance(
                OnboardingUI(
                    R.drawable.background_search_gradient,
                    context.getString(R.string.onboarding_search_title),
                    context.getString(R.string.onboarding_search_description)
                )
            )
            2 -> return OnboardingFragment.newInstance(
                OnboardingUI(
                    R.drawable.background_archive_gradient,
                    context.getString(R.string.onboarding_archive_title),
                    context.getString(R.string.onboarding_archive_description)
                )
            )
            3 -> return OnboardingFragment.newInstance(
                OnboardingUI(
                    R.drawable.background_contact_gradient,
                    context.getString(R.string.onboarding_contact_title),
                    context.getString(R.string.onboarding_contact_description)
                )
            )
            else -> return null
        }
    }

    companion object {
        private val NUM_ITEMS = 4
    }

}