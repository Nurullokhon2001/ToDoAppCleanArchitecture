package com.example.to_doappcleanarchitecture.presentation.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_doappcleanarchitecture.data.database.ToDoDatabase
import com.example.to_doappcleanarchitecture.data.database.ToDoRepository
import com.example.to_doappcleanarchitecture.data.model.ToDoData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateViewModel(application: Application) : AndroidViewModel(application) {

    private val toDoDao = ToDoDatabase.getDatabase(
        application
    ).toDoDao()
    private val repository: ToDoRepository = ToDoRepository(toDoDao)

//    fun updateData(toDoData: ToDoData) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.updateData(toDoData)
//        }
//    }
}