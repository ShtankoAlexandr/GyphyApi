package com.example.myapplication.features.books.presentation.viewmodel.state


import com.example.myapplication.features.books.domain.model.GifEntity

sealed class GifsVS {
    class Entities(val entities: GifEntity):GifsVS()
    class Error(val message:String?):GifsVS()
    class ShowLoader(val showLoader:Boolean):GifsVS()
}