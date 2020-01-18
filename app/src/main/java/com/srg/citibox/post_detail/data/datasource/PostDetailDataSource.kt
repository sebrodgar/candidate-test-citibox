package com.srg.citibox.post_detail.data.datasource

import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.CitiboxResult
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.common.data.model.User

/**
 * Created by Sebastián Rodríguez on 15,January,2020
 */

interface PostDetailDataSource {

    suspend fun getAuthorByPost(
        userId: Long,
        onResult: (CitiboxResult<CitiboxError, User>) -> Unit
    )

    suspend fun getNumberOfCommentsByPost(
        postId: Long,
        onResult: (CitiboxResult<CitiboxError, Int>) -> Unit
    )
}