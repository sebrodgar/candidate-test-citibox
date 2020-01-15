package com.srg.citibox.post_list.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.post_list.domain.usecase.GetAllPostList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Sebastián Rodríguez on 14,January,2020
 */

class PostListViewModel @Inject constructor(private val getAllPostList: GetAllPostList) : ViewModel() {

    var posts: MutableLiveData<List<Post>> = MutableLiveData(emptyList())
    var errorLive: MutableLiveData<CitiboxError> = MutableLiveData()

    fun getPosts(){
        viewModelScope.launch {
            getAllPostList.getAllPostList { data, error ->
                posts.postValue(data)
                errorLive.postValue(error)
            }
        }


    }


}