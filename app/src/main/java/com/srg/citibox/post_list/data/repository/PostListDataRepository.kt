package com.srg.citibox.post_list.data.repository

import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.post_list.data.datasource.CloudPostListDataSource
import com.srg.citibox.post_list.data.datasource.LocalPostListDataSource
import com.srg.citibox.post_list.domain.repository.PostListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Sebastián Rodríguez on 14,January,2020
 */

/*class PostListDataRepository(
    private val cloudPostListDataSource: CloudPostListDataSource,
    private val localPostListDataSource: LocalPostListDataSource
): PostListRepository {
*/
class PostListDataRepository @Inject constructor(
    private val cloudPostListDataSource: CloudPostListDataSource,
    private val localPostListDataSource: LocalPostListDataSource
) : PostListRepository {

    override suspend fun getAllPosts(onResult: (data: List<Post>?, error: CitiboxError?) -> Unit) {
        cloudPostListDataSource.getAllPosts(onResult)
    }
}