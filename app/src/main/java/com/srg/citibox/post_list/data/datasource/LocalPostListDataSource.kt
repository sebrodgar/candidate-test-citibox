package com.srg.citibox.post_list.data.datasource

import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.CitiboxResult
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.post_list.ui.PostListViewModel

/**
 * Created by Sebastián Rodríguez on 14,January,2020
 */

class LocalPostListDataSource: PostsListDataSource {

    override suspend fun getAllPosts(onResult: (CitiboxResult<CitiboxError, List<Post>>) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}