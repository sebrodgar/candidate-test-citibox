package com.srg.citibox.post_list.domain.usecase

import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.CitiboxResult
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.post_list.domain.repository.PostListRepository
import com.srg.citibox.post_list.ui.PostListViewModel
import javax.inject.Inject

/**
 * Created by Sebastián Rodríguez on 14,January,2020
 */

class GetAllPostList @Inject constructor (private val postListRepository: PostListRepository) {

    suspend fun getAllPostList(onResult: (CitiboxResult<CitiboxError, List<Post>>) -> Unit){
        postListRepository.getAllPosts {
            onResult(it)
        }

    }
}