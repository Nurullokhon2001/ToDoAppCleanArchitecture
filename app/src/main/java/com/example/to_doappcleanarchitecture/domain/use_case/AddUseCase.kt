package com.example.to_doappcleanarchitecture.domain.use_case

import com.example.to_doappcleanarchitecture.data.repository.ToDoRepositoryImpl
import com.example.to_doappcleanarchitecture.domain.model.ToDoData

class AddUseCase(private val repository: ToDoRepositoryImpl) {
    suspend operator fun invoke(toDoData: ToDoData) {
        repository.insertData(toDoData = toDoData)
    }
}