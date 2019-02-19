package com.example.cansearch.trial

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.cansearch.R
import com.example.cansearch.core.getColorCompat
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.disease_synonyms_compound.view.*


class DiseaseCompoundView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.disease_synonyms_compound, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        // todo -
        // disease.prefferedName.forEach{}
        for (i in 1..8) {
            val chip = Chip(context)
            chip.setChipBackgroundColorResource(R.color.colorPrimary)
            chip.setTextColor(context.getColorCompat(android.R.color.white))
            chip.text = "Item ${i}"
            disease_chip_group.addView(chip)
        }
    }

}