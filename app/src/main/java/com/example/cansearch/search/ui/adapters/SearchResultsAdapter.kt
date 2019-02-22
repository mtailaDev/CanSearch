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
import com.example.cansearch.search.ui.SearchListItem
import kotlinx.android.synthetic.main.item_list_search.view.*

class SearchResultsAdapter(
    private val searchResults: List<SearchListItem>,
    private var archiveListener: onArchiveClickHandler
) :
    RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val quickSearchView = inflater.inflate(R.layout.item_list_search, parent, false)
        return ViewHolder(quickSearchView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.setData(searchResults[position])

        viewHolder.itemView.search_results_parent.setOnClickListener {
            archiveListener.onTrialSelected()
        }

        viewHolder.itemView.search_result_archive_icon.setOnClickListener {
            val x = archiveListener.onArchive()

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
        fun setData(trial: SearchListItem) {
            itemView.search_result_study_title.text = trial.briefTitle
            itemView.search_result_principle_investigator.text = trial.principleInvestigator
            itemView.search_result_leading_organization.text = trial.leadOrganization
            itemView.search_result_location.text = trial.totalSites
            itemView.search_result_phase_status.text = trial.phase

            // todo - check against cached list for matching nci ID's - display correct icon
        }

        fun setIcon(saved: Boolean) {
            if (saved) {
                itemView.search_result_archive_icon.setImageDrawable(itemView.context.resources.getDrawable(R.drawable.ic_archive_selected))
            } else {
                itemView.search_result_archive_icon.setImageDrawable(itemView.context.resources.getDrawable(R.drawable.ic_archive_unselected))
            }
        }
    }

    interface onArchiveClickHandler {
        fun onArchive(): Boolean
        fun onTrialSelected()
    }
}