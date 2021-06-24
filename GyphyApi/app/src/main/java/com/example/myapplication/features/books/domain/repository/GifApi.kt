package com.example.myapplication.features.books.domain.repository

import com.example.myapplication.features.books.domain.model.GifEntity
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit interface for Trending API endpoint.
 */
interface GifApi {

    @GET("/v1/gifs/trending")
    suspend fun loadGif2(@Query("api_key") apiKey: String,
                                @Query("limit") limit: Int): GifEntity
}