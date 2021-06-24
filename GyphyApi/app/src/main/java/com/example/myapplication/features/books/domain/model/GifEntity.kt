package com.example.myapplication.features.books.domain.model

data class GifEntity(val data: ArrayList<Gif>)

data class Gif(val id: String, val url: String, val images: Images)

data class Images(val original: Original)

data class Original(val url: String)