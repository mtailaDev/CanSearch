package com.example.cansearch.search.di

import com.example.cansearch.core.di.AppComponent
import com.example.cansearch.search.SearchFragment
import dagger.Component

@Search
@Component(modules = [SearchModule::class], dependencies = [AppComponent::class])
interface SearchComponent {


    fun inject(fragment: SearchFragment)

    @Component.Builder
    interface Builder {
        fun searchModule(module: SearchModule): Builder
        fun appComponent(module: AppComponent): Builder
        fun build(): SearchComponent
    }
}