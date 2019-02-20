package com.example.cansearch.core

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat

fun View.gone(){
    this.visibility = View.GONE
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)