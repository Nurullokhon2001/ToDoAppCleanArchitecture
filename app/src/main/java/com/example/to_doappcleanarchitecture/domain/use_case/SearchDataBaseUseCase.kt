package com.example.to_doappcleanarchitecture.domain.use_case

import androidx.lifecycle.LiveData
import com.example.to_doappcleanarchitecture.domain.model.ToDoData
import com.example.to_doappcleanarchitecture.domain.repository.ToDoRepository

class SearchDataBaseUseCase(val repository: ToDoRepository) {
    operator fun invoke(string: String): LiveData<List<ToDoData>> {
        return repository.searchDatabase(string)
    }
}