package com.gmail.notifytask3.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.notifytask3.repository.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory @AssistedInject constructor(
    private val repository: UserRepository,
    @Assisted("email") private val email: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            DetailsViewModel::class.java -> DetailsViewModel(repository, email) as T
            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }

    @AssistedFactory
    interface Factory {

        fun create(@Assisted("email") email: String): DetailsViewModelFactory
    }
}