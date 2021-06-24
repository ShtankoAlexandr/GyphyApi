package com.example.myapplication.features.books.data.datasource.rest

import com.example.myapplication.core.network.BaseApiClient
import com.example.myapplication.features.books.domain.repository.GifApi

object GifApiClient : BaseApiClient<GifApi>(GifApi::class.java)