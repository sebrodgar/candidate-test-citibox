package com.srg.citibox.post_detail.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.srg.citibox.common.TestData
import com.srg.citibox.common.data.model.*
import com.srg.citibox.common.data.network.retrofit.ClientApi
import com.srg.citibox.post_detail.data.datasource.CloudPostDetailDataSource
import com.srg.citibox.post_detail.data.repository.PostDetailDataRepository
import com.srg.citibox.post_list.data.datasource.CloudPostListDataSource
import com.srg.citibox.post_list.data.repository.PostListDataRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.junit.Assert.*
import org.mockito.Mock
import retrofit2.Response


/**
 * Created by Sebastián Rodríguez on 16,January,2020
 */


class PostDetailDataRepositoryTest : TestData() {

    lateinit var cloudPostDetailDataSource: CloudPostDetailDataSource
    lateinit var postDetailDataRepository: PostDetailDataRepository

    private lateinit var expectedResponseUsers: Response<List<User>>
    private lateinit var expectedResponseComments: Response<List<Comment>>


    @Before
    fun init() {
        cloudPostDetailDataSource = CloudPostDetailDataSource(clientApi)
        postDetailDataRepository = PostDetailDataRepository(cloudPostDetailDataSource)

    }

    @Test
    fun `get post detail successful response`() = runBlocking {

        expectedResponseUsers = Response.success(200, mockUserList)
        expectedResponseComments = Response.success(200, mockComments)


        Mockito.`when`(clientApi.getAllUsers()).thenReturn(expectedResponseUsers)
        Mockito.`when`(clientApi.getAllComments()).thenReturn(expectedResponseComments)

        postDetailDataRepository.getAuthorByPost(1) {
            assertEquals(it.isFailure, false)
            assertEquals(it.isSuccess, true)
        }

    }

    @Test
    fun `get post detail error response`() = runBlocking {

        expectedResponseUsers = responseErrorUser
        expectedResponseComments = responseErrorComment


        Mockito.`when`(clientApi.getAllUsers()).thenReturn(expectedResponseUsers)
        Mockito.`when`(clientApi.getAllComments()).thenReturn(expectedResponseComments)

        postDetailDataRepository.getAuthorByPost(1) {
            assertEquals(it.isFailure, true)
            assertEquals(it.isSuccess, false)
        }

    }
}