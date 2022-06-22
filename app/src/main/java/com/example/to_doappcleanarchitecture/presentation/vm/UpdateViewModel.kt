package com.example.to_doappcleanarchitecture.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_doappcleanarchitecture.data.repository.ToDoRepositoryImpl
import com.example.to_doappcleanarchitecture.domain.model.ToDoData
import com.example.to_doappcleanarchitecture.domain.use_case.DeleteDataUseCase
import com.example.to_doappcleanarchitecture.domain.use_case.UpdateDataUseCase
import com.example.to_doappcleanarchitecture.domain.use_case.UpdateUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateViewModel(repository: ToDoRepositoryImpl) : ViewModel() {

    private val updateDataUseCase: UpdateDataUseCase = UpdateDataUseCase(repository)
    private val deleteDataUseCase: DeleteDataUseCase = DeleteDataUseCase(repository)
    private val updateUseCase: UpdateUseCase = UpdateUseCase(
        updateDataUseCase = updateDataUseCase,
        deleteDataUseCase = deleteDataUseCase
    )

    fun updateData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            updateUseCase.updateDataUseCase.invoke(toDoData = toDoData)
        }
    }

    fun deleteData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            updateUseCase.deleteDataUseCase.invoke(toDoData = toDoData)
        }
    }
}