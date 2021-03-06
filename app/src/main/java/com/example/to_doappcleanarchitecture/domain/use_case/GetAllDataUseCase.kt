package com.example.to_doappcleanarchitecture.domain.use_case

import androidx.lifecycle.LiveData
import com.example.to_doappcleanarchitecture.domain.model.ToDoData
import com.example.to_doappcleanarchitecture.domain.repository.ToDoRepository

class GetAllDataUseCase(private val repository: ToDoRepository) {
     operator fun invoke(): LiveData<List<ToDoData>> {
        return repository.getAllData()
    }
}