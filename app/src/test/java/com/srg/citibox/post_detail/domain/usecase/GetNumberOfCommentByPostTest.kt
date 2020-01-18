package com.srg.citibox.post_detail.domain.usecase

import com.srg.citibox.common.TestData
import com.srg.citibox.common.data.model.Comment
import com.srg.citibox.common.data.model.User
import com.srg.citibox.common.data.model.foldSuccess
import com.srg.citibox.common.data.network.retrofit.ClientApi
import com.srg.citibox.post_detail.data.datasource.CloudPostDetailDataSource
import com.srg.citibox.post_detail.data.repository.PostDetailDataRepository
import com.srg.citibox.post_detail.domain.repository.PostDetailRepository
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

class GetNumberOfCommentByPostTest : TestData() {


    lateinit var cloudPostDetailDataSource: CloudPostDetailDataSource
    lateinit var postDetailRepository: PostDetailRepository

    lateinit var getNumberOfCommentByPost: GetNumberOfCommentByPost


    private lateinit var expectedResponse: Response<List<Comment>>

    @Before
    fun init() {
        cloudPostDetailDataSource = CloudPostDetailDataSource(clientApi)
        postDetailRepository = PostDetailDataRepository(cloudPostDetailDataSource)

        getNumberOfCommentByPost = GetNumberOfCommentByPost(postDetailRepository)

    }

    @Test
    fun `get number of comment by post successful response`() = runBlocking {

        expectedResponse = Response.success(200, mockComments)

        Mockito.`when`(clientApi.getAllComments()).thenReturn(expectedResponse)

        getNumberOfCommentByPost.getNumberOfCommentByPost(1) {
            Assert.assertEquals(it.isFailure, false)
            Assert.assertEquals(it.isSuccess, true)

            Assert.assertEquals(it.foldSuccess(), 1)
        }

    }

    @Test
    fun `get number of comment by post error response`() = runBlocking {

        expectedResponse = responseErrorComment

        Mockito.`when`(clientApi.getAllComments()).thenReturn(expectedResponse)

        getNumberOfCommentByPost.getNumberOfCommentByPost(1) {
            Assert.assertEquals(it.isFailure, true)
            Assert.assertEquals(it.isSuccess, false)
        }

    }
}