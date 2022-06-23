package com.example.to_doappcleanarchitecture.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_doappcleanarchitecture.domain.model.ToDoData
import com.example.to_doappcleanarchitecture.domain.use_case.DeleteAllDataUseCase
import com.example.to_doappcleanarchitecture.domain.use_case.GetAllDataUseCase
import com.example.to_doappcleanarchitecture.domain.use_case.SearchDataBaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ListViewModel : ViewModel(), KoinComponent {

    private val getAllDataUseCase by inject<GetAllDataUseCase>()
    private val deleteAllDataUseCase by inject<DeleteAllDataUseCase>()
    private val searchDataBaseUseCase by inject<SearchDataBaseUseCase>()

    val getAllData: LiveData<List<ToDoData>> = getAllDataUseCase.invoke()

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllDataUseCase.invoke()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>> {
        return searchDataBaseUseCase.invoke(searchQuery)
    }


//    val sortByHighPriority: LiveData<List<ToDoData>> = repository.sortByHighPriority
//    val sortByLowPriority: LiveData<List<ToDoData>> = repository.sortByLowPriority
}