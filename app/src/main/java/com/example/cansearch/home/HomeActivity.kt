package com.example.cansearch.home

import android.animation.*
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.cansearch.R
import com.example.cansearch.core.gone
import com.example.cansearch.core.visible
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private var bottomNavEnabled = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        parent_container.layoutTransition.setDuration(100)
        setNavClickListener()
        setDestinationListener()
    }

    private fun setDestinationListener() {
        val nav = Navigation.findNavController(this, R.id.nav_host_fragment)
        nav.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.trialFragment -> {
                    animateDownBottomNav()
                    bottomNavEnabled = false
                }
                R.id.searchFragment,
                R.id.archiveFragment,
                R.id.settingsFragment -> {
                    if (!bottomNavEnabled) {
                        animateUpBottomNav()
                    }
                    bottomNavEnabled = true
                }
            }
        }
    }

    private fun setNavClickListener() {
        home_bottom_nav.setupWithNavController(Navigation.findNavController(this, R.id.nav_host_fragment))
    }

    private fun animateDownBottomNav() {
        val dp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56f, home_bottom_nav.context.resources.displayMetrics)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(home_bottom_nav, View.ALPHA, 0f),
            ObjectAnimator.ofFloat(home_bottom_nav, View.TRANSLATION_Y, dp)
        )
        animatorSet.duration = 100
        animatorSet.interpolator = AccelerateInterpolator()
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                home_bottom_nav.gone()
            }
        })
        animatorSet.start()
    }

    private fun animateUpBottomNav() {
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(home_bottom_nav, View.ALPHA, 1f),
            ObjectAnimator.ofFloat(home_bottom_nav, View.TRANSLATION_Y, 0f)
        )
        animatorSet.duration = 300
        animatorSet.interpolator = FastOutSlowInInterpolator()
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                home_bottom_nav.visible()
            }
        })
        animatorSet.start()
    }


}