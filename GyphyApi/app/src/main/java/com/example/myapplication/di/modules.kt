package com.example.myapplication.di

import com.example.myapplication.features.books.data.datasource.rest.GifDataStore
import com.example.myapplication.features.books.data.repository.GifRepositoryImpl
import com.example.myapplication.features.books.domain.interactor.GetGifInteractor
import com.example.myapplication.features.books.domain.repository.GifApi
import com.example.myapplication.features.books.presentation.viewmodel.GifsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val postModule = module {

    //region ViewModel
    viewModel {
        GifsViewModel(get())
    }

    //endregion

    //region Interactor
    single {
        GetGifInteractor(
            get()
        )
    }
    //endregion

    //region Repository
    single<GifApi> {
        GifRepositoryImpl(get())
    }
    //endregion

    //region Datastore
    single {
        GifDataStore()
    }
    //endregion
}

val modulesArr = listOf(postModule)