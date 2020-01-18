package com.srg.citibox.post_detail.data

import com.srg.citibox.common.TestData
import com.srg.citibox.common.data.model.foldSuccess
import com.srg.citibox.post_detail.data.datasource.CloudPostDetailDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import retrofit2.Response

/**
 * Created by Sebastián Rodríguez on 17,January,2020
 */

class CloudPostDetailDataSourceTest : TestData() {

    private lateinit var cloudPostDetailDataSource: CloudPostDetailDataSource


    @Before
    fun init() {
        cloudPostDetailDataSource = CloudPostDetailDataSource(clientApi)

    }

    @Test
    fun `get author by post successful response`() = runBlocking {

        val expectedResponse = Response.success(200, mockUserList)

        `when`(clientApi.getAllUsers()).thenReturn(expectedResponse)

        cloudPostDetailDataSource.getAuthorByPost(1) {
            assertEquals(it.isFailure, false)
            assertEquals(it.isSuccess, true)

        }

    }

    @Test
    fun `get author by post error response`() = runBlocking {

        val expectedResponse = responseErrorUser

        `when`(clientApi.getAllUsers()).thenReturn(expectedResponse)

        cloudPostDetailDataSource.getAuthorByPost(1) {
            assertEquals(it.isFailure, true)
            assertEquals(it.isSuccess, false)

        }

    }


    @Test
    fun `get number of comments by post successful response`() = runBlocking {

        val expectedResponse = Response.success(200, mockComments)

        `when`(clientApi.getAllComments()).thenReturn(expectedResponse)

        cloudPostDetailDataSource.getNumberOfCommentsByPost(1) {
            assertEquals(it.isFailure, false)
            assertEquals(it.isSuccess, true)

            assertEquals(it.foldSuccess(), 1)

        }

    }

    @Test
    fun `get number of comments by post error response`() = runBlocking {

        val expectedResponse = responseErrorComment

        `when`(clientApi.getAllComments()).thenReturn(expectedResponse)

        cloudPostDetailDataSource.getNumberOfCommentsByPost(1) {
            assertEquals(it.isFailure, true)
            assertEquals(it.isSuccess, false)


        }

    }

}