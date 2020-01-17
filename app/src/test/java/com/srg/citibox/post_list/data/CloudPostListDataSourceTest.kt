package com.srg.citibox.post_list.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.notNull
import com.nhaarman.mockitokotlin2.whenever
import com.srg.citibox.common.TestData
import com.srg.citibox.common.data.model.*
import com.srg.citibox.common.data.network.retrofit.ClientApi
import com.srg.citibox.post_list.data.datasource.CloudPostListDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

import retrofit2.Response
import retrofit2.mock.Calls
import org.junit.Assert.*

/**
 * Created by Sebastián Rodríguez on 17,January,2020
 */

class CloudPostListDataSourceTest : TestData() {

    @Mock
    private lateinit var clientApi: ClientApi

    @Mock
    private lateinit var cloudPostListDataSource: CloudPostListDataSource

    private lateinit var expectedResponse: Response<List<Post>>

    private lateinit var onResult: CitiboxResult<CitiboxError?, List<Post>?>


    @Before
    fun init() {

        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `get all post successful response`() = runBlocking {

        //expectedResponse = Response.success(200, mockPostList)
        onResult = (CitiboxResult.Success(mockPostList))
        `when`(cloudPostListDataSource.getAllPosts { onResult}).thenReturn(Unit)
        assertNotNull(onResult.foldSuccess())
        assertNull(onResult.foldFailure())

    }

    @Test
    fun `get all post error response`() = runBlocking {

        //expectedResponse = Response.success(200, mockPostList)
        onResult = (CitiboxResult.Failure(CitiboxError.internalServerError()))
        `when`(cloudPostListDataSource.getAllPosts { onResult }).thenReturn(Unit)
        assertNotNull(onResult.foldFailure())
        assertNull(onResult.foldSuccess())

    }
}