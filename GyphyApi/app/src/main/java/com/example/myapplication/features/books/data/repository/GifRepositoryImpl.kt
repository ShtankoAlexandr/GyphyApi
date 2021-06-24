package com.example.myapplication.features.books.data.repository

import com.example.myapplication.features.books.data.datasource.rest.GifDataStore
import com.example.myapplication.features.books.domain.model.GifEntity
import com.example.myapplication.features.books.domain.repository.GifApi


class GifRepositoryImpl(
    private val GifDataStore: GifDataStore
) : GifApi {

    override suspend fun loadGif2(apiKey: String, limit: Int): GifEntity = GifDataStore.getTrends2(apiKey, limit)
}
