package com.example.cansearch.login.ui

import com.example.cansearch.R

sealed class OnboardingUI(val backgroundGradient: Int, val title: String, val description: String) {
    object Welcome : OnboardingUI(R.drawable.background_welcome_gradient, "ASD", "ASDas")
    object Search : OnboardingUI(123, "ASD", "ASDas")
    object Archive : OnboardingUI(123, "ASD", "ASDas")
    object Contact : OnboardingUI(123, "ASD", "ASDas")
}