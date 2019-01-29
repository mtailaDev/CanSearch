package com.example.cansearch.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cansearch.R
import com.example.cansearch.login.ui.OnboardingUI
import kotlinx.android.synthetic.main.fragment_onboarding.*


class OnboardingFragment : Fragment() {

    lateinit var onboardingUI: OnboardingUI

    companion object {

        private const val UI_THEME = "ui theme"

        fun newInstance(onboardingUI: OnboardingUI): OnboardingFragment {
            val bundle = Bundle()
            val fragment = OnboardingFragment()
            bundle.putParcelable(UI_THEME, onboardingUI)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onboardingUI = arguments?.get(UI_THEME) as OnboardingUI
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onboarding_tv_subtitle.text = onboardingUI.title
        onboarding_tv_description.text = onboardingUI.description
        onboarding_parent_container.background = context?.getDrawable(onboardingUI.backgroundGradient)
    }
}