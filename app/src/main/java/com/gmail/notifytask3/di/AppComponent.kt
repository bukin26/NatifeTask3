package com.gmail.notifytask3.di

import android.content.Context
import com.gmail.notifytask3.presentation.details.DetailsFragment
import com.gmail.notifytask3.presentation.list.ListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class, RepositoryModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun injectListFragment(listFragment: ListFragment)

    fun injectDetailsFragment(detailsFragment: DetailsFragment)
}