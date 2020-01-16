package com.srg.citibox.post_detail.data.datasource

import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.User

/**
 * Created by Sebastián Rodríguez on 15,January,2020
 */

interface PostDetailDataSource {

    suspend fun getAuthorByPost(
        postId: Long,
        onResult: (data: User?, error: CitiboxError?) -> Unit
    )

    suspend fun getNumberOfCommentsByPost(
        postId: Long,
        onResult: (data: Int?, error: CitiboxError?) -> Unit
    )
}