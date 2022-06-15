package com.example.to_doappcleanarchitecture.presentation.vm

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.to_doappcleanarchitecture.core.BaseViewModel
import com.example.to_doappcleanarchitecture.domain.model.ToDoData
import com.example.to_doappcleanarchitecture.domain.use_case.DeleteAllDataUseCase
import com.example.to_doappcleanarchitecture.domain.use_case.GetAllDataUseCase
import com.example.to_doappcleanarchitecture.domain.use_case.ListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : BaseViewModel(application) {

    private val getAllDataUseCase: GetAllDataUseCase = GetAllDataUseCase(repository)
    private val deleteAllDataUseCase: DeleteAllDataUseCase = DeleteAllDataUseCase(repository)
    private val listUseCase: ListUseCase = ListUseCase(getAllDataUseCase, deleteAllDataUseCase)

    val getAllData: LiveData<List<ToDoData>> = listUseCase.getAllDataUseCase.invoke()

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            listUseCase.deleteAllDataUseCase()
        }
    }

//    val sortByHighPriority: LiveData<List<ToDoData>> = repository.sortByHighPriority
//    val sortByLowPriority: LiveData<List<ToDoData>> = repository.sortByLowPriority
//
//
//    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>>{
//        return repository.searchDatabase(searchQuery)
//    }


}