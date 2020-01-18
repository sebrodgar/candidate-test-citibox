package com.srg.citibox.post_detail.data.datasource

import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.CitiboxResult
import com.srg.citibox.common.data.model.Post
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
        onResult: (CitiboxResult<CitiboxError, User>) -> Unit
    ) {

        withContext(Dispatchers.IO) {
            val response = api.getAllUsers()

            if (response.isSuccessful) {
                val user =
                    response.body()?.let { users -> users.find { user -> user.id == userId } }
                user?.let { onResult(CitiboxResult.Success(user)) }
                    ?: onResult(CitiboxResult.Failure(CitiboxError.unknownError()))

            } else {
                onResult(
                    CitiboxResult.Failure(
                        CitiboxError(
                            CitiboxError.CitiboxErrorType.get(response.code()),
                            response.message()
                        )
                    )
                )

            }
        }
    }


    override suspend fun getNumberOfCommentsByPost(
        postId: Long,
        onResult: (CitiboxResult<CitiboxError, Int>) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            val response = api.getAllComments()

            if (response.isSuccessful) {
                val numberOfComments = response.body()
                    ?.let { comments -> comments.filter { comment -> comment.postId == postId } }
                    ?.size
                numberOfComments?.let { onResult(CitiboxResult.Success(numberOfComments)) } ?: onResult(
                    CitiboxResult.Failure(
                    CitiboxError.unknownError())
                )

            } else {
                onResult(
                    CitiboxResult.Failure(
                        CitiboxError.unknownError())
                )
            }
        }
    }


}