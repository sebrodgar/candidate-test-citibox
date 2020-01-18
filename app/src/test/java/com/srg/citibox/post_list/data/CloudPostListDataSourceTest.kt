package com.srg.citibox.post_list.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.notNull
import com.nhaarman.mockitokotlin2.verify
import com.srg.citibox.common.TestData
import com.srg.citibox.common.data.model.*
import com.srg.citibox.common.data.network.retrofit.ClientApi
import com.srg.citibox.post_list.data.datasource.CloudPostListDataSource
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkClass
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

import retrofit2.Response
import retrofit2.mock.Calls
import org.junit.Assert.*
import org.mockito.Mockito.times

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