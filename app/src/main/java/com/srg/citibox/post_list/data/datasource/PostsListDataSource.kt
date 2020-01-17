package com.srg.citibox.post_list.data.datasource

import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.Post

/**
 * Created by Sebastián Rodríguez on 12,January,2020
 */

interface PostsListDataSource {

    suspend fun getAllPosts(onResult: (data: List<Post>?, error: CitiboxError?) -> Unit)

}