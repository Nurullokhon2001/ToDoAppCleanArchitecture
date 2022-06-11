package com.example.to_doappcleanarchitecture.presentation.vm

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.to_doappcleanarchitecture.core.BaseViewModel
import com.example.to_doappcleanarchitecture.domain.model.ToDoData
import com.example.to_doappcleanarchitecture.domain.use_case.UpdateUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateViewModel(application: Application) : BaseViewModel(application) {

    private val updateUseCase: UpdateUseCase = UpdateUseCase(repository)

    fun updateData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            updateUseCase.invoke(toDoData = toDoData)
        }
    }

}