package com.example.cansearch.search.di

import com.example.cansearch.App

object SearchDagger {
    val component: SearchComponent = DaggerSearchComponent.builder()
        .appComponent(App.daggerAppComponent())
        .searchModule(SearchModule())
        .build()
}