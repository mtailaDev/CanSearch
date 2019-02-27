package com.example.cansearch.search.ui.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.cansearch.R
import com.example.cansearch.core.getColorCompat
import com.example.cansearch.core.gone
import com.example.cansearch.core.visible
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.associated_disease_info_compound.view.*

class AssociatedDiseaseInfoCompoundView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {

    lateinit var chipType: ChipType

    init {
        inflate(context, R.layout.associated_disease_info_compound, this)
    }

    fun setCardTitle(cardTitle: String) {
        chips_card_title.text = cardTitle
    }

    private fun setChipGroup(chipColor: Int, chipTitles: List<String>?) {
        if (chipTitles != null){
            for (title in chipTitles) {
                val chip = Chip(context)
                chip.setChipBackgroundColorResource(chipColor)
                chip.setTextColor(context.getColorCompat(android.R.color.white))
                chip.text = title
                disease_chip_group.addView(chip)
            }
        }
    }

    fun setData(chipColor: Int, associatedData: List<String>?, chipType: ChipType) {

        this.chipType = chipType

        if (associatedData?.size!! > MAX_LIST_SIZE) {
            setChipGroup(chipColor, associatedData.subList(0, 19))
            study_associated_btn.visible()
        } else {
            setChipGroup(chipColor, associatedData)
            study_associated_btn.gone()
        }
    }

    companion object {
        const val MAX_LIST_SIZE = 20
    }

    enum class ChipType{
        DISEASE,
        BIOMARKERS
    }
}