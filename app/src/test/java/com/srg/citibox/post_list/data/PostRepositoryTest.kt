package com.srg.citibox.post_list.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.srg.citibox.common.TestData
import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.common.data.model.foldFailure
import com.srg.citibox.common.data.model.foldSuccess
import com.srg.citibox.post_list.data.datasource.CloudPostListDataSource
import com.srg.citibox.post_list.data.repository.PostListDataRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.junit.Assert.*



/**
 * Created by Sebastián Rodríguez on 16,January,2020
 */


class PostRepositoryTest : TestData() {

    lateinit var cloudPostListDataSource: CloudPostListDataSource
    lateinit var postListDataRepository: PostListDataRepository

    @Before
    fun init() {
        cloudPostListDataSource = Mockito.mock(CloudPostListDataSource::class.java)
        postListDataRepository = Mockito.mock(PostListDataRepository::class.java)

        //cloudPostDetailDataSource = Mockito.mock(CloudPostDetailDataSource::class.java)
    }

    @Test
    fun `get post list successful response`() = runBlocking{

        assertNotNull(cloudPostListDataSource)
        assertNotNull(postListDataRepository)

        whenever(cloudPostListDataSource.getAllPosts(onResult = any())).thenAnswer {
            val r = it.getArgument<(data:List<Post>?, error: CitiboxError?) -> Unit>(0)
            r.invoke(mockPostList, null)
        }

        postListDataRepository.getAllPosts { result ->
            assertNull(result.foldFailure())
            assertNotNull(result.foldSuccess())
            assertEquals(mockPostList, result.foldSuccess())
            print("Successful response ${result.foldSuccess().toString()}")
        }
    }
    @Test
    fun `get post list error response`() = runBlocking{

        assertNotNull(cloudPostListDataSource)
        assertNotNull(postListDataRepository)

        whenever(cloudPostListDataSource.getAllPosts(onResult = any())).thenAnswer {
            val onResult = it.getArgument<(data:List<Post>?, error: CitiboxError?) -> Unit>(0)
            onResult.invoke(null, networkError)
        }

        postListDataRepository.getAllPosts { result ->
            assertNull(result.foldSuccess())
            assertNotNull(result.foldFailure())
            assertEquals(result.foldFailure(), networkError)
            print("Error response ${result.foldFailure().toString()}")
        }
    }
}