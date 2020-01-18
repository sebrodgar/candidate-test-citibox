package com.srg.citibox.post_list.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.srg.citibox.common.TestData
import com.srg.citibox.common.data.model.*
import com.srg.citibox.common.data.network.retrofit.ClientApi
import com.srg.citibox.post_list.data.datasource.CloudPostListDataSource
import com.srg.citibox.post_list.data.repository.PostListDataRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.`when`
import retrofit2.Response


/**
 * Created by Sebastián Rodríguez on 16,January,2020
 */


class PostListDataRepositoryTest : TestData() {

    lateinit var cloudPostListDataSource: CloudPostListDataSource
    lateinit var postListDataRepository: PostListDataRepository

    private lateinit var expectedResponse: Response<List<Post>>

    @Before
    fun init() {
        cloudPostListDataSource = CloudPostListDataSource(clientApi)
        postListDataRepository = PostListDataRepository(cloudPostListDataSource)

    }

    @Test
    fun `get post list successful response`() = runBlocking {

        expectedResponse = Response.success(200, mockPostList)

        `when`(clientApi.getAllPosts()).thenReturn(expectedResponse)

        postListDataRepository.getAllPosts {
            assertEquals(it.isFailure, false)
            assertEquals(it.isSuccess, true)
        }

    }

    @Test
    fun `get post list error response`() = runBlocking {

        expectedResponse = responseErrorPost

        `when`(clientApi.getAllPosts()).thenReturn(expectedResponse)

        postListDataRepository.getAllPosts {
            assertEquals(it.isFailure, true)
            assertEquals(it.isSuccess, false)
        }

    }

}