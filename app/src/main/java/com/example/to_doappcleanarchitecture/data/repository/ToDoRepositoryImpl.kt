package com.example.to_doappcleanarchitecture.data.repository

import androidx.lifecycle.LiveData
import com.example.to_doappcleanarchitecture.data.dao.ToDoDao
import com.example.to_doappcleanarchitecture.domain.model.ToDoData
import com.example.to_doappcleanarchitecture.domain.repository.ToDoRepository

class ToDoRepositoryImpl(private val toDoDao: ToDoDao) : ToDoRepository {

    override fun getAllData(): LiveData<List<ToDoData>> {
        return toDoDao.getAllData()
    }

    override fun searchDatabase(string: String): LiveData<List<ToDoData>> {
        return toDoDao.searchInDatabase(string)
    }

    override suspend fun insertData(toDoData: ToDoData) {
        toDoDao.insertData(toDoData = toDoData)
    }

    override suspend fun updateData(toDoData: ToDoData) {
        toDoDao.updateData(toDoData = toDoData)
    }

    override suspend fun deleteData(toDoData: ToDoData) {
        toDoDao.deleteData(toDoData = toDoData)
    }

    override suspend fun deleteAllData() {
        toDoDao.deleteAllData()
    }
}