package com.example.to_doappcleanarchitecture.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_doappcleanarchitecture.data.repository.ToDoRepositoryImpl
import com.example.to_doappcleanarchitecture.domain.model.ToDoData
import com.example.to_doappcleanarchitecture.domain.use_case.AddUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(repository: ToDoRepositoryImpl) : ViewModel() {

    private val addUseCase: AddUseCase = AddUseCase(repository)

    fun insertData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            addUseCase.invoke(toDoData)
        }
    }
}