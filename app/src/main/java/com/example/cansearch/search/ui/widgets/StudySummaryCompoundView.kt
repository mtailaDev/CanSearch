package com.example.cansearch.search.ui.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.cansearch.R
import com.example.cansearch.search.domain.SearchScreen
import kotlinx.android.synthetic.main.study_summary_compound.view.*

class StudySummaryCompoundView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.study_summary_compound, this)
    }

    fun setData(studySummary: SearchScreen.SearchResult.StudySummary) {
        study_summary_description.text = studySummary.briefDescription
    }
}