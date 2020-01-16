package com.srg.citibox.post_detail.domain.usecase

import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.User
import com.srg.citibox.post_detail.domain.repository.PostDetailRepository
import javax.inject.Inject

/**
 * Created by Sebastián Rodríguez on 16,January,2020
 */

class GetAuthorByPost @Inject constructor(private val postDetailRepository: PostDetailRepository) {

    suspend fun getAuthorByPost(
        userId: Long,
        onResult: (data: User?, error: CitiboxError?) -> Unit
    ) {

        postDetailRepository.getAuthorByPost(userId, onResult)

    }
}