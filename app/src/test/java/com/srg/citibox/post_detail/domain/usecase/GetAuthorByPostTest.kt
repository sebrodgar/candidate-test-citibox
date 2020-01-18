package com.srg.citibox.post_detail.domain.usecase

import com.srg.citibox.common.TestData
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.common.data.model.User
import com.srg.citibox.common.data.network.retrofit.ClientApi
import com.srg.citibox.post_detail.data.datasource.CloudPostDetailDataSource
import com.srg.citibox.post_detail.data.repository.PostDetailDataRepository
import com.srg.citibox.post_detail.domain.repository.PostDetailRepository
import com.srg.citibox.post_list.data.datasource.CloudPostListDataSource
import com.srg.citibox.post_list.data.repository.PostListDataRepository
import com.srg.citibox.post_list.domain.repository.PostListRepository
import com.srg.citibox.post_list.domain.usecase.GetAllPostList
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

class GetAuthorByPostTest : TestData() {

    lateinit var cloudPostDetailDataSource: CloudPostDetailDataSource
    lateinit var postDetailRepository: PostDetailRepository

    lateinit var getAuthorByPost: GetAuthorByPost

    private lateinit var expectedResponse: Response<List<User>>

    @Before
    fun init() {
        cloudPostDetailDataSource = CloudPostDetailDataSource(clientApi)
        postDetailRepository = PostDetailDataRepository(cloudPostDetailDataSource)

        getAuthorByPost = GetAuthorByPost(postDetailRepository)

    }

    @Test
    fun `get author by post successful response`() = runBlocking {

        expectedResponse = Response.success(200, mockUserList)

        Mockito.`when`(clientApi.getAllUsers()).thenReturn(expectedResponse)

        getAuthorByPost.getAuthorByPost(1) {
            Assert.assertEquals(it.isFailure, false)
            Assert.assertEquals(it.isSuccess, true)
        }

    }

    @Test
    fun `get author by post error response`() = runBlocking {

        expectedResponse = responseErrorUser

        Mockito.`when`(clientApi.getAllUsers()).thenReturn(expectedResponse)

        getAuthorByPost.getAuthorByPost(1) {
            Assert.assertEquals(it.isFailure, true)
            Assert.assertEquals(it.isSuccess, false)
        }

    }
}