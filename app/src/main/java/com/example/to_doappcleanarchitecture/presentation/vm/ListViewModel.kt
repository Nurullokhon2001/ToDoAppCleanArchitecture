package com.example.to_doappcleanarchitecture.presentation.vm

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.to_doappcleanarchitecture.core.BaseViewModel
import com.example.to_doappcleanarchitecture.domain.model.ToDoData
import com.example.to_doappcleanarchitecture.domain.use_case.GetAllDataUseCase

class ListViewModel(application: Application) : BaseViewModel(application) {

    private val getAllDataUseCase: GetAllDataUseCase = GetAllDataUseCase(repository)
    val getAllData: LiveData<List<ToDoData>> = getAllDataUseCase.invoke()


//    val sortByHighPriority: LiveData<List<ToDoData>> = repository.sortByHighPriority
//    val sortByLowPriority: LiveData<List<ToDoData>> = repository.sortByLowPriority
//
//
//    fun deleteAll() {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.deleteAll()
//        }
//    }
//
//    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>>{
//        return repository.searchDatabase(searchQuery)
//    }


}