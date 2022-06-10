package com.example.to_doappcleanarchitecture.data.database

import androidx.lifecycle.LiveData
import com.example.to_doappcleanarchitecture.data.dao.ToDoDao
import com.example.to_doappcleanarchitecture.data.model.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData) {
        toDoDao.insertData(toDoData)
    }

}