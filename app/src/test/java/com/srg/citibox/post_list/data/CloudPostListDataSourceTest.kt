package com.srg.citibox.post_list.data

import com.srg.citibox.common.TestData
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.post_list.data.datasource.CloudPostListDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import retrofit2.Response

/**
 * Created by Sebastián Rodríguez on 17,January,2020
 */

class CloudPostListDataSourceTest : TestData() {

    private lateinit var cloudPostListDataSource: CloudPostListDataSource

    private lateinit var expectedResponse: Response<List<Post>>

    @Before
    fun init() {

        cloudPostListDataSource = CloudPostListDataSource(clientApi)

    }

    @Test
    fun `get all post successful response`() = runBlocking {

        expectedResponse = Response.success(200, mockPostList)

        `when`(clientApi.getAllPosts()).thenReturn(expectedResponse)

        cloudPostListDataSource.getAllPosts {
            assertEquals(it.isFailure, false)
            assertEquals(it.isSuccess, true)

        }

    }

    @Test
    fun `get all post error response`() = runBlocking {

        expectedResponse = Response.error(500, ResponseBody.create(null, "Internal Error"))

        `when`(clientApi.getAllPosts()).thenReturn(expectedResponse)

        cloudPostListDataSource.getAllPosts {
            assertEquals(it.isFailure, true)
            assertEquals(it.isSuccess, false)
        }

    }
}