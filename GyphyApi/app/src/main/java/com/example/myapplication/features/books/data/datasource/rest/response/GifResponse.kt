package com.example.myapplication.features.books.data.datasource.rest.response

/**
 * Represents a trending from Giphy Gif API.
 */
data class GifResponse(val data: List<DataResponse>)
data class DataResponse(val slug: String, val images: ImagesResponse)
data class ImagesResponse(val fixed_width: ImageSizeResponse,
                          val fixed_height: ImageSizeResponse,
                          val original: ImageSizeResponse)
data class ImageSizeResponse(val url: String)