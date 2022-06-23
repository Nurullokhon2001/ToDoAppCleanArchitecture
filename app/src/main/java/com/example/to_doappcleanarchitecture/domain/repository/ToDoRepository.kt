package com.example.to_doappcleanarchitecture.domain.repository

import androidx.lifecycle.LiveData
import com.example.to_doappcleanarchitecture.domain.model.ToDoData

interface ToDoRepository {

    fun getAllData(): LiveData<List<ToDoData>>
    fun searchDatabase(string: String): LiveData<List<ToDoData>>
    suspend fun insertData(toDoData: ToDoData)
    suspend fun updateData(toDoData: ToDoData)
    suspend fun deleteData(toDoData: ToDoData)
    suspend fun deleteAllData()

}