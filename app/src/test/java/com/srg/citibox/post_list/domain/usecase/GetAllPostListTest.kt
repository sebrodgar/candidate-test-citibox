package com.srg.citibox.post_list.domain.usecase

import com.srg.citibox.common.TestData
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.common.data.network.retrofit.ClientApi
import com.srg.citibox.post_list.data.datasource.CloudPostListDataSource
import com.srg.citibox.post_list.data.repository.PostListDataRepository
import com.srg.citibox.post_list.domain.repository.PostListRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Response

/**
 * Created by Sebastián Rodríguez on 18,January,2020
 */

class GetAllPostListTest: TestData() {

    lateinit var cloudPostListDataSource: CloudPostListDataSource
    lateinit var postListRepository: PostListRepository

    lateinit var getAllPostList: GetAllPostList

    private lateinit var expectedResponse: Response<List<Post>>

    @Before
    fun init() {
        cloudPostListDataSource = CloudPostListDataSource(clientApi)
        postListRepository = PostListDataRepository(cloudPostListDataSource)

        getAllPostList = GetAllPostList(postListRepository)

    }

    @Test
    fun `get post list successful response`() = runBlocking {

        expectedResponse = Response.success(200, mockPostList)

        Mockito.`when`(clientApi.getAllPosts()).thenReturn(expectedResponse)

        getAllPostList.getAllPostList {
            Assert.assertEquals(it.isFailure, false)
            Assert.assertEquals(it.isSuccess, true)
        }

    }

    @Test
    fun `get post list error response`() = runBlocking {

        expectedResponse = responseErrorPost

        Mockito.`when`(clientApi.getAllPosts()).thenReturn(expectedResponse)

        getAllPostList.getAllPostList {
            Assert.assertEquals(it.isFailure, true)
            Assert.assertEquals(it.isSuccess, false)
        }

    }

}