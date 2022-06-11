package com.example.to_doappcleanarchitecture.presentation.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.to_doappcleanarchitecture.data.database.ToDoDatabase
import com.example.to_doappcleanarchitecture.data.database.ToDoRepository
import com.example.to_doappcleanarchitecture.data.model.ToDoData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(application: Application) : AndroidViewModel(application) {

    private val toDoDao = ToDoDatabase.getDatabase(
        application
    ).toDoDao()
    private val repository: ToDoRepository = ToDoRepository(toDoDao)


    fun insertData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData)
        }
    }

//    fun deleteItem(toDoData: ToDoData) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.deleteItem(toDoData)
//        }
//    }


}