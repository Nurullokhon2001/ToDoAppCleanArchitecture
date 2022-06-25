package com.example.to_doappcleanarchitecture

import androidx.room.Room
import com.example.to_doappcleanarchitecture.data.local.database.ToDoDatabase
import com.example.to_doappcleanarchitecture.data.local.repository.ToDoRepositoryImpl
import com.example.to_doappcleanarchitecture.domain.repository.ToDoRepository
import com.example.to_doappcleanarchitecture.domain.use_case.*
import com.example.to_doappcleanarchitecture.firebase.FireBaseViewModel
import com.example.to_doappcleanarchitecture.presentation.vm.AddViewModel
import com.example.to_doappcleanarchitecture.presentation.vm.ListViewModel
import com.example.to_doappcleanarchitecture.presentation.vm.UpdateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    single { Room.databaseBuilder(get(), ToDoDatabase::class.java, "notes").build() }
    single { get<ToDoDatabase>().toDoDao() }
    single<ToDoRepository> { ToDoRepositoryImpl(get()) }
}

val useCaseModule = module {
    singleOf(::AddUseCase)
    singleOf(::DeleteAllDataUseCase)
    singleOf(::DeleteDataUseCase)
    singleOf(::GetAllDataUseCase)
    singleOf(::UpdateDataUseCase)
    singleOf(::SearchDataBaseUseCase)
}

val viewModel = module {
    viewModelOf(::UpdateViewModel)
    viewModelOf(::AddViewModel)
    viewModel { ListViewModel() }
    viewModelOf(::FireBaseViewModel)


}