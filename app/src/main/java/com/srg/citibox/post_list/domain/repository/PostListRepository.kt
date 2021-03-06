package com.srg.citibox.post_list.domain.repository

import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.CitiboxResult
import com.srg.citibox.common.data.model.Post

/**
 * Created by Sebastián Rodríguez on 14,January,2020
 */

interface PostListRepository {

    suspend fun getAllPosts(onResult: (CitiboxResult<CitiboxError, List<Post>>) -> Unit)
}