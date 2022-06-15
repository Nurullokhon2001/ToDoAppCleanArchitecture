package com.example.to_doappcleanarchitecture.domain.use_case

data class ListUseCase(
    val getAllDataUseCase: GetAllDataUseCase,
    val deleteAllDataUseCase: DeleteAllDataUseCase
)