package com.faztbit.alwaopportunity.di

import androidx.room.Room
import com.faztbit.alwaopportunity.data.database.AppDatabase
import com.faztbit.alwaopportunity.data.database.dao.DataBaseDao
import com.faztbit.alwaopportunity.data.repository.MainRepository
import com.faztbit.alwaopportunity.data.repository.MainRepositoryImpl
import com.faztbit.alwaopportunity.domain.useCases.GetDataUseCase
import com.faztbit.alwaopportunity.domain.mappers.GeneralMapper
import com.faztbit.alwaopportunity.domain.mappers.GeneralMapperImpl
import com.faztbit.alwaopportunity.domain.useCases.GetMachinesByPriorityUseCase
import com.faztbit.alwaopportunity.domain.useCases.GetPrioritiesUseCase
import com.faztbit.alwaopportunity.domain.useCases.RegisterNewMachineUseCase
import com.faztbit.alwaopportunity.features.dashboard.MainViewModel
import com.faztbit.alwaopportunity.features.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { RegisterViewModel(get(), get()) }
}
val appModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(context = get(), AppDatabase::class.java, "machine_dataBase")
            .build()
    }
    single<DataBaseDao> { get<AppDatabase>().dataBaseDao() }

    single<MainRepository> { MainRepositoryImpl(get()) }
    single<GeneralMapper> { GeneralMapperImpl() }

    factory { GetDataUseCase(get(), get()) }
    factory { GetPrioritiesUseCase(get()) }
    factory { GetMachinesByPriorityUseCase(get()) }
    factory { RegisterNewMachineUseCase(get(), get()) }
}