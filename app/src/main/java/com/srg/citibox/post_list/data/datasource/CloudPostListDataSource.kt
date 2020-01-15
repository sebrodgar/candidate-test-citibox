package com.srg.citibox.post_list.data.datasource

import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.common.data.network.retrofit.ClientApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Sebastián Rodríguez on 14,January,2020
 */

class CloudPostListDataSource @Inject constructor(private val api: ClientApi) : PostsListDataSource {


    override suspend fun getAllPosts(onResult: (data: List<Post>?, error: CitiboxError?) -> Unit) {

        withContext(Dispatchers.IO) {
            val response = api.getAllPosts()

            if (response.isSuccessful) {
                onResult(response.body(), null)
            } else {
                onResult(
                    null,
                    CitiboxError(
                        CitiboxError.CitiboxErrorType.get(response.code()),
                        response.message()
                    ))
            }
        }

    }
}