package com.srg.citibox.common.data.network.retrofit

import com.srg.citibox.common.data.model.Comment
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.common.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * Created by Sebastián Rodríguez on 12,January,2020
 */

interface ClientApi {

    @Headers("Content-Type: application/json")
    @GET("posts")
    suspend fun getAllPosts(): Response<List<Post>>

    @Headers("Content-Type: application/json")
    @GET("users")
    suspend fun getUserByPostId(
        @Path("postId") postId: Long
    ): Response<List<User>>


    @Headers("Content-Type: application/json")
    @GET("comments")
    suspend fun getAllComments(): Response<List<Comment>>

    @Headers("Content-Type: application/json")
    @GET("comments")
    fun getCommentsByPost(
        @Path("postId") postId: Long
    ): Response<List<Comment>>

    @Headers("Content-Type: application/json")
    @GET("users")
    suspend fun getAllUsers(): Response<List<User>>


}