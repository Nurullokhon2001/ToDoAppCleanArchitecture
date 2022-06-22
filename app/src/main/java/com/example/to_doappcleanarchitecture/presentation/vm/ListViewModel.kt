package com.example.to_doappcleanarchitecture.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_doappcleanarchitecture.domain.model.ToDoData
import com.example.to_doappcleanarchitecture.domain.use_case.ListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(val repository: ListUseCase) : ViewModel() {

    val getAllData: LiveData<List<ToDoData>> = repository.getAllDataUseCase.invoke()

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllDataUseCase.invoke()
        }
    }

//    val sortByHighPriority: LiveData<List<ToDoData>> = repository.sortByHighPriority
//    val sortByLowPriority: LiveData<List<ToDoData>> = repository.sortByLowPriority
//
//
//    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>>{
//        return repository.searchDatabase(s earchQuery)
//    }


}