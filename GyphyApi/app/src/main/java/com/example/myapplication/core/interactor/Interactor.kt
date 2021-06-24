package com.example.myapplication.core.interactor

interface Interactor<in Params, out Type> {
    suspend fun execute2(apiKey: String, limit: Int, params: Params): Type

    object None
}