package com.example.to_doappcleanarchitecture


import com.example.to_doappcleanarchitecture.data.database.ToDoDatabase
import com.example.to_doappcleanarchitecture.data.repository.ToDoRepositoryImpl
import com.example.to_doappcleanarchitecture.domain.repository.ToDoRepository
import com.example.to_doappcleanarchitecture.domain.use_case.*
import com.example.to_doappcleanarchitecture.presentation.vm.ListViewModel
import com.example.to_doappcleanarchitecture.presentation.vm.UpdateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val databaseModule = module {
    single {
        ToDoDatabase.getDatabase(
            get()
        ).toDoDao()
    }

    single<ToDoRepository> { ToDoRepositoryImpl(toDoDao = get()) }

}

val useCaseModule = module {
    factory<AddUseCase> {
        AddUseCase(repository = get())
    }

    factory<DeleteAllDataUseCase> {
        DeleteAllDataUseCase(repository = get())
    }

    factory<DeleteDataUseCase> {
        DeleteDataUseCase(repository = get())
    }

    factory<ListUseCase> {
        ListUseCase(getAllDataUseCase = get(), deleteAllDataUseCase = get())
    }

    factory<GetAllDataUseCase> {
        GetAllDataUseCase(repository = get())
    }


    factory<UpdateDataUseCase> {
        UpdateDataUseCase(repository = get())
    }

    factory<UpdateUseCase> {
        UpdateUseCase(
            updateDataUseCase = get(),
            deleteDataUseCase = get()
        )
    }
}

val viewModel = module {

    viewModel<ListViewModel> {
        ListViewModel(
            repository = get()
        )
    }

    viewModel<UpdateViewModel> {
        UpdateViewModel(
            repository = get()
        )
    }
}