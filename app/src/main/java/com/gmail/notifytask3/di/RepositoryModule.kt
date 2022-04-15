package com.gmail.notifytask3.di

import com.gmail.notifytask3.repository.UserRepository
import com.gmail.notifytask3.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository
}