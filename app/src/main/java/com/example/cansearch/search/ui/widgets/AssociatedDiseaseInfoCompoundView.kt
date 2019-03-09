package com.example.cansearch.search.ui.widgets

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.cansearch.R
import com.example.cansearch.core.getColorCompat
import com.example.cansearch.search.domain.DiseaseExtras
import com.example.cansearch.search.domain.SearchScreen
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.associated_disease_info_compound.view.*


class AssociatedDiseaseInfoCompoundView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    var diseaseExtraExpanded = false

    init {
        inflate(context, R.layout.associated_disease_info_compound, this)
    }

    fun setData(diseaseExtras: List<DiseaseExtras>) {

        diseaseExtras.forEach {
            val chip = Chip(context)
            chip.chipStrokeWidth = 2f
            if (it is SearchScreen.SearchResult.AssociatedDiseases.Disease) {
                chip.setChipBackgroundColorResource(R.color.chip_color_disease_background)
                chip.chipStrokeColor = ColorStateList.valueOf(context.getColorCompat(R.color.chip_color_disease_stroke))
                chip.setTextColor(context.getColorCompat(R.color.chip_color_disease_stroke))
                chip.text = it.title
            } else if (it is SearchScreen.SearchResult.AssociatedBiomarkers.BioMarkers) {
                chip.setChipBackgroundColorResource(R.color.chip_color_genes_background)
                chip.chipStrokeColor = ColorStateList.valueOf(context.getColorCompat(R.color.chip_color_gene_stroke))
                chip.setTextColor(context.getColorCompat(R.color.chip_color_gene_stroke))
                chip.text = it.title
            }
            disease_extra_chip_group.addView(chip)
        }


        // todo - will still need to do this later once I filter common disease types
//        if (associatedData?.size!! > MAX_LIST_SIZE) {
//            setChipGroup(chipColor, associatedData.subList(0, 19))
//            study_associated_btn.visible()
//        } else {
//            setChipGroup(chipColor, associatedData)
//            study_associated_btn.gone()
//        }
    }

    fun clearChipGroup(){
        disease_extra_chip_group.removeAllViews()
    }


//    fun setData(chipColor: Int, associatedData: List<String>?, chipType: ChipType) {
//
//        this.chipType = chipType
//
//
//        // todo - will still need to do this later once I filter common disease types
////        if (associatedData?.size!! > MAX_LIST_SIZE) {
////            setChipGroup(chipColor, associatedData.subList(0, 19))
////            study_associated_btn.visible()
////        } else {
////            setChipGroup(chipColor, associatedData)
////            study_associated_btn.gone()
////        }
//    }

    companion object {
        const val MAX_LIST_SIZE = 20
    }
}