package com.gmail.notifytask3.di

import android.content.Context
import androidx.room.Room
import com.gmail.notifytask3.data.AppDatabase
import com.gmail.notifytask3.data.UsersDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "Users.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUsersDao(database: AppDatabase): UsersDao {
        return database.usersDao()
    }
}