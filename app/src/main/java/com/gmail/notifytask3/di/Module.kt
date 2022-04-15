package com.gmail.notifytask3.di

import android.content.Context
import androidx.room.Room
import com.gmail.notifytask3.data.AppDatabase
import com.gmail.notifytask3.data.UsersDao
import com.gmail.notifytask3.data.UsersService
import com.gmail.notifytask3.presentation.details.DetailsViewModel
import com.gmail.notifytask3.presentation.list.ListViewModel
import com.gmail.notifytask3.repository.UserRepository
import com.gmail.notifytask3.repository.UserRepositoryImpl
import com.gmail.notifytask3.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { ListViewModel(get()) }
    viewModel { parameters -> DetailsViewModel(repository = get(), email = parameters.get()) }
}

val repositoryModule = module {

    single<UserRepository> { UserRepositoryImpl(get(), get()) }
}

val databaseModule = module {

    fun provideAppDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "Users.db"
        ).build()
    }

    fun provideUsersDao(database: AppDatabase): UsersDao {
        return database.usersDao()
    }

    single { provideAppDataBase(androidContext()) }
    single { provideUsersDao(get()) }
}

val networkModule = module {

    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS }
    }

    fun provideOkHttpClient(logger: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logger).build()
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideUsersService(retrofit: Retrofit): UsersService {
        return retrofit.create(UsersService::class.java)
    }

    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideUsersService(get()) }
}