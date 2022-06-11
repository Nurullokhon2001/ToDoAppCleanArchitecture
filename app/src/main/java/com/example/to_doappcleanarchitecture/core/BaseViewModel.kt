package com.example.to_doappcleanarchitecture.core

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.to_doappcleanarchitecture.data.database.ToDoDatabase
import com.example.to_doappcleanarchitecture.data.repository.ToDoRepositoryImpl

open class BaseViewModel(application: Application):AndroidViewModel(application) {

    private val toDoDao = ToDoDatabase.getDatabase(
        application
    ).toDoDao()

    protected val repository: ToDoRepositoryImpl = ToDoRepositoryImpl(toDoDao)

}