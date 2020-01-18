package com.srg.citibox.post_list.data.datasource

import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.CitiboxResult
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.common.data.network.retrofit.ClientApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Sebastián Rodríguez on 14,January,2020
 */

class CloudPostListDataSource @Inject constructor(private val api: ClientApi) : PostsListDataSource {


    override suspend fun getAllPosts(onResult: (CitiboxResult<CitiboxError, List<Post>>) -> Unit) {

        withContext(Dispatchers.IO) {
            val response = api.getAllPosts()
            if (response.isSuccessful) {
                response.body()?.let {
                    onResult(CitiboxResult.Success(it))
                } ?: onResult(CitiboxResult.Failure(CitiboxError.unknownError()))
            } else {
                onResult(CitiboxResult.Failure(CitiboxError(
                    CitiboxError.CitiboxErrorType.get(response.code()),
                    response.message()
                )))

            }
        }

    }

}