package com.example.cansearch.search.ui.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.cansearch.R

class StudySummaryCompoundView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.study_summary_compound, this)
    }
}