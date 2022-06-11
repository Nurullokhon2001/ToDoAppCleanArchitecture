package com.example.to_doappcleanarchitecture.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.to_doappcleanarchitecture.domain.model.ToDoData

@Dao
interface ToDoDao {

    @Query("Select * FROM  todo_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)

    @Update
    suspend fun updateData(toDoData: ToDoData)
}