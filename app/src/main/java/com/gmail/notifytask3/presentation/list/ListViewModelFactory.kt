package com.gmail.notifytask3.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.notifytask3.repository.ListRepository

@Suppress("UNCHECKED_CAST")
class ListViewModelFactory(
    private val repository: ListRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when (modelClass) {
            ListViewModel::class.java -> return ListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}