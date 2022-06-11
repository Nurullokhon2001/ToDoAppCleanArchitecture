package com.example.to_doappcleanarchitecture.presentation.vm

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.to_doappcleanarchitecture.core.BaseViewModel
import com.example.to_doappcleanarchitecture.domain.model.ToDoData
import com.example.to_doappcleanarchitecture.domain.use_case.AddUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(application: Application) : BaseViewModel(application) {

    private val addUseCase : AddUseCase = AddUseCase(repository)

    fun insertData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            addUseCase.invoke(toDoData)
        }
    }
}