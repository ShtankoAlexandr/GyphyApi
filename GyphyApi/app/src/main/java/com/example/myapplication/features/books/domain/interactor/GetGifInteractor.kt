package com.example.myapplication.features.books.domain.interactor

import com.example.myapplication.core.interactor.Interactor
import com.example.myapplication.features.books.domain.model.GifEntity
import com.example.myapplication.features.books.domain.repository.GifApi

class GetGifInteractor(
    private val gifApi: GifApi
) : Interactor<Interactor.None, Any> {

    override suspend fun execute2(apiKey: String, limit: Int, params: Interactor.None): GifEntity {
        return gifApi.loadGif2(apiKey, limit)
    }


}