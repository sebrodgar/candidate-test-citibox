package com.srg.citibox.post_detail.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srg.citibox.common.data.model.*
import com.srg.citibox.post_detail.domain.usecase.GetAuthorByPost
import com.srg.citibox.post_detail.domain.usecase.GetNumberOfCommentByPost
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Sebastián Rodríguez on 16,January,2020
 */

class PostDetailViewModel @Inject constructor(
    private val getAuthorByPost: GetAuthorByPost,
    private val getNumberOfCommentByPost: GetNumberOfCommentByPost
) : ViewModel() {

    var author: MutableLiveData<User> = MutableLiveData()
    var numberOfComment: MutableLiveData<Int> = MutableLiveData()
    var post: MutableLiveData<Post> = MutableLiveData()
    var errorLive: MutableLiveData<CitiboxError> = MutableLiveData()

    fun getPostDetails(post: Post) {
        this.post.value = post
        viewModelScope.launch {
            val calls = listOf(     // fetch two call at the same time
                async {
                    getAuthorByPost.getAuthorByPost(post.userId) { result ->

                        if(result.isSuccess)
                            author.postValue(result.foldSuccess())
                        else
                            errorLive.postValue(result.foldFailure())
                    }

                },  // async returns a result for the first call
                async {
                    getNumberOfCommentByPost.getNumberOfCommentByPost(post.id) { result ->
                        if(result.isSuccess)
                            numberOfComment.postValue(result.foldSuccess())
                        else
                            errorLive.postValue(result.foldFailure())
                    }
                }   // async returns a result for the second call
            )
            calls.awaitAll()
        }
    }


}