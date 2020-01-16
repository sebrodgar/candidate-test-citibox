package com.srg.citibox.post_detail.data.datasource

import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.User
import com.srg.citibox.common.data.network.retrofit.ClientApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Sebastián Rodríguez on 16,January,2020
 */

class CloudPostDetailDataSource @Inject constructor(private val api: ClientApi) :
    PostDetailDataSource {

    override suspend fun getAuthorByPost(
        userId: Long,
        onResult: (data: User?, error: CitiboxError?) -> Unit
    ) {

        withContext(Dispatchers.IO){
            val response = api.getAllUsers()

            if (response.isSuccessful) {
                val user = response.body()?.let { users -> users.find { user -> user.id == userId } }
                user?.let { onResult(it, null) } ?: onResult(null, CitiboxError.unknownError())

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

    override suspend fun getNumberOfCommentsByPost(
        postId: Long,
        onResult: (data: Int?, error: CitiboxError?) -> Unit
    ) {
        withContext(Dispatchers.IO){
            val response = api.getAllComments()

            if (response.isSuccessful) {
                val numberOfComments = response.body()?.let { comments -> comments.filter { comment -> comment.postId == postId }}?.size
                numberOfComments?.let { onResult(it, null) } ?: onResult(null, CitiboxError.unknownError())

            } else {
                onResult(
                    null,
                    CitiboxError(
                        CitiboxError.CitiboxErrorType.get(response.code()),
                        response.message()
                    ))
            }
        }    }


}