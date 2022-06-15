package com.example.to_doappcleanarchitecture.domain.use_case

import com.example.to_doappcleanarchitecture.domain.model.ToDoData
import com.example.to_doappcleanarchitecture.domain.repository.ToDoRepository

class DeleteDataUseCase(private val repository: ToDoRepository) {
    suspend operator fun invoke(toDoData: ToDoData) {
        repository.deleteData(toDoData = toDoData)
    }
}