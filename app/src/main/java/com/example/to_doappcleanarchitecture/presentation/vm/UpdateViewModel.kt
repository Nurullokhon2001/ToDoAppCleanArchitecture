package com.example.to_doappcleanarchitecture.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_doappcleanarchitecture.domain.model.ToDoData
import com.example.to_doappcleanarchitecture.domain.use_case.DeleteDataUseCase
import com.example.to_doappcleanarchitecture.domain.use_case.UpdateDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpdateViewModel : ViewModel(), KoinComponent {

    private val updateDataUseCase by inject<UpdateDataUseCase>()
    private val deleteDataUseCase by inject<DeleteDataUseCase>()

    fun updateData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            updateDataUseCase.invoke(toDoData = toDoData)
        }
    }

    fun deleteData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteDataUseCase.invoke(toDoData = toDoData)
        }
    }
}