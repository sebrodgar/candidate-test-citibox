package com.srg.citibox.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.srg.citibox.common.TestData
import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.post_list.data.datasource.CloudPostListDataSource
import com.srg.citibox.post_list.data.datasource.PostsListDataSource
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
    lateinit var postsListDataSource: PostsListDataSource
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

        postListDataRepository.getAllPosts { data, error ->
            assertNull(error)
            assertNotNull(data)
            assertEquals(mockPostList, data)
            print("Successful response ${data.toString()}")
        }
    }
}