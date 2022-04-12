package com.gmail.notifytask3.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.notifytask3.repository.DetailsRepository

@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(
    private val repository: DetailsRepository,
    private val email: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when (modelClass) {
            DetailsViewModel::class.java -> return DetailsViewModel(repository, email) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}