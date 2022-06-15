package com.example.to_doappcleanarchitecture.domain.use_case

import com.example.to_doappcleanarchitecture.domain.repository.ToDoRepository

class DeleteAllDataUseCase(private val repository: ToDoRepository) {
    suspend operator fun invoke() {
        repository.deleteAllData()
    }
}