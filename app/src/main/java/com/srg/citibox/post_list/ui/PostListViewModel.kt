package com.srg.citibox.post_list.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.common.data.model.foldFailure
import com.srg.citibox.common.data.model.foldSuccess
import com.srg.citibox.post_list.domain.usecase.GetAllPostList
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Sebastián Rodríguez on 14,January,2020
 */

class PostListViewModel @Inject constructor(private val getAllPostList: GetAllPostList) :
    ViewModel() {

    var posts: MutableLiveData<List<Post>> = MutableLiveData(emptyList())
    var errorLive: MutableLiveData<CitiboxError> = MutableLiveData()
    var _showLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    var _showList: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getPosts() {
        viewModelScope.launch {
            getAllPostList.getAllPostList { result ->
                _showLoading.value?.let {
                    if (it) {
                        _showLoading.postValue(false)
                        _showList.postValue(true)
                    }
                }

                if (result.isSuccess)
                    posts.postValue(result.foldSuccess())
                else
                    errorLive.postValue(result.foldFailure())
            }
        }

    }

}