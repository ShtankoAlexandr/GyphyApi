package com.example.myapplication.features.books.data.datasource.rest

import com.example.myapplication.features.books.domain.model.GifEntity

class GifDataStore {
    suspend fun getTrends2(apiKey: String, limit: Int): GifEntity =
        GifApiClient.getApiClient().loadGif2(apiKey, limit)
}