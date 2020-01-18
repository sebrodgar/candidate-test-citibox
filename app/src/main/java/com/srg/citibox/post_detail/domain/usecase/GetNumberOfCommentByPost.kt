package com.srg.citibox.post_detail.domain.usecase

import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.CitiboxResult
import com.srg.citibox.common.data.model.User
import com.srg.citibox.post_detail.domain.repository.PostDetailRepository
import javax.inject.Inject

/**
 * Created by Sebastián Rodríguez on 16,January,2020
 */

class GetNumberOfCommentByPost @Inject constructor(private val postDetailRepository: PostDetailRepository) {

    suspend fun getNumberOfCommentByPost(
        postId: Long,
        onResult: (CitiboxResult<CitiboxError, Int>) -> Unit
    ) {

        postDetailRepository.getNumberOfCommentsByPost(postId, onResult)

    }

}