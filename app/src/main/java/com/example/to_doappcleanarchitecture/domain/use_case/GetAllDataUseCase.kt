package com.example.to_doappcleanarchitecture.domain.use_case

import androidx.lifecycle.LiveData
import com.example.to_doappcleanarchitecture.data.repository.ToDoRepositoryImpl
import com.example.to_doappcleanarchitecture.domain.model.ToDoData

class GetAllDataUseCase(private val repository: ToDoRepositoryImpl) {
     operator fun invoke(): LiveData<List<ToDoData>> {
        return repository.getAllData()
    }
}