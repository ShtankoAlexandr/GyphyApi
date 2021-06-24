 package com.example.myapplication.features.books.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.interactor.Interactor
import com.example.myapplication.core.utils.io
import com.example.myapplication.core.utils.ui
import com.example.myapplication.features.books.domain.interactor.GetGifInteractor
import com.example.myapplication.features.books.presentation.viewmodel.state.GifsVS
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class GifsViewModel(
    private val getPostsInteractor: GetGifInteractor
) : ViewModel(), KoinComponent {

    val viewState: LiveData<GifsVS> get() = mViewState
    private val mViewState = MutableLiveData<GifsVS>()



    fun getPosts(apiKey: String, limit: Int) {
        viewModelScope.launch {
            mViewState.value = GifsVS.ShowLoader(true)
            try {
                io {
                    Log.e("aaa", "${getPostsInteractor.execute2(apiKey, limit, Interactor.None)}")
                    mViewState.postValue(
                        GifsVS.Entities(
                            getPostsInteractor.execute2(
                                apiKey,
                                limit,
                                Interactor.None
                            )
                        )
                    )
                    /*getPostsInteractor.execute2(apiKey, limit, Interactor.None)
                        .collect {
                        Log.e("aaa", "$it")
                        //ui { mViewState.value = PostsVS.AddPost(mPostVMMapper.map(it)) }
                        }*/
                }
            } catch (e: Exception) {
                e.printStackTrace()
                ui { mViewState.value = GifsVS.Error(e.message) }
            }
            mViewState.value = GifsVS.ShowLoader(false)
        }
    }
}