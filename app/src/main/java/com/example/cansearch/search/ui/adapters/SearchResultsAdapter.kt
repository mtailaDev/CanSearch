package com.example.cansearch.search.ui.adapters

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.example.cansearch.R
import com.example.cansearch.search.domain.SearchResultSummary
import com.example.cansearch.search.domain.SearchScreen
import kotlinx.android.synthetic.main.item_list_search.view.*

class SearchResultsAdapter(
    private val searchResults: List<SearchScreen.SearchResult>,
    private var trialSelectedListener: OnTrialSelectedListener
) :
    RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val quickSearchView = inflater.inflate(R.layout.item_list_search, parent, false)
        return ViewHolder(quickSearchView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.setData(SearchResultSummary.mapFromSearchResult(searchResults[position]))

        val container = viewHolder.itemView.search_results_parent
        container.setOnClickListener {
            trialSelectedListener.onTrialSelected(searchResults[position])
        }

        viewHolder.itemView.search_result_archive_icon.setOnClickListener {
            val x = trialSelectedListener.onArchive()

            val popAnimationY =
                ObjectAnimator.ofFloat(viewHolder.itemView.search_result_archive_icon, View.SCALE_Y, 1f, 1.5f)
            val popAnimationX =
                ObjectAnimator.ofFloat(viewHolder.itemView.search_result_archive_icon, View.SCALE_X, 1f, 1.5f)
            val popAnimation = AnimatorSet()
            popAnimation.duration = 75
            popAnimation.interpolator = OvershootInterpolator(2f)
            popAnimation.playTogether(popAnimationX, popAnimationY)
            popAnimation.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    viewHolder.setIcon(x)
                    val popAnimationY =
                        ObjectAnimator.ofFloat(viewHolder.itemView.search_result_archive_icon, View.SCALE_Y, 1.5f, 1f)
                    val popAnimationX =
                        ObjectAnimator.ofFloat(viewHolder.itemView.search_result_archive_icon, View.SCALE_X, 1.5f, 1f)
                    val popAnimation = AnimatorSet()
                    popAnimation.playTogether(popAnimationX, popAnimationY)
                    popAnimation.duration = 50
                    popAnimation.interpolator = AccelerateDecelerateInterpolator()
                    popAnimation.start()
                }
            })
            popAnimation.start()

        }
    }

    override fun getItemCount(): Int {
        return searchResults.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setData(result: SearchResultSummary) {
            itemView.search_result_study_title.text = result.briefTitle
            itemView.search_result_principle_investigator.text = result.principleInvestigator
            itemView.search_result_leading_organization.text = result.leadOrganization
            itemView.search_result_location.text = result.totalSites
            itemView.search_result_phase_status.text = result.phase
        }

        fun setIcon(saved: Boolean) {
            if (saved) {
                itemView.search_result_archive_icon.setImageDrawable(itemView.context.resources.getDrawable(R.drawable.ic_archive_selected))
            } else {
                itemView.search_result_archive_icon.setImageDrawable(itemView.context.resources.getDrawable(R.drawable.ic_archive_unselected))
            }
        }
    }

    // todo - rename this
    interface OnTrialSelectedListener {
        fun onArchive(): Boolean
        fun onTrialSelected(selectedTrial: SearchScreen.SearchResult)
    }
}