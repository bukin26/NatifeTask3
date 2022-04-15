package com.gmail.notifytask3.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.notifytask3.repository.UserRepository
import javax.inject.Inject


class ListViewModelFactory @Inject constructor(
    private val repository: UserRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            ListViewModel::class.java -> ListViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}
